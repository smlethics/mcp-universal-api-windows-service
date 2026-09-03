package it.salvatoremichelelombardo.mcpapi.api;

import it.salvatoremichelelombardo.mcpapi.config.ApiCatalogProperties;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class HttpApiTransport implements ApiTransport {
    private final ApiCatalog catalog; private final UrlBuilder urlBuilder; private final EnvironmentValueResolver resolver; private final HttpClient httpClient;
    public HttpApiTransport(ApiCatalog catalog, UrlBuilder urlBuilder, EnvironmentValueResolver resolver) { this.catalog=catalog; this.urlBuilder=urlBuilder; this.resolver=resolver; this.httpClient=HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).followRedirects(HttpClient.Redirect.NORMAL).build(); }
    @Override public ApiInvocationResult invoke(ApiInvocationRequest request) {
        var api=catalog.api(request.api()); var operation=catalog.operation(request.api(), request.operation());
        String url=urlBuilder.build(resolver.resolve(api.getBaseUrl()), operation.getPath(), request.pathParameters(), request.queryParameters());
        var builder=HttpRequest.newBuilder(URI.create(url)).timeout(Duration.ofSeconds(30)).header("Accept", "application/json");
        Map<String,String> headers=new LinkedHashMap<>(); headers.putAll(operation.getHeaders()); if(request.headers()!=null) headers.putAll(request.headers()); applyAuthentication(headers, api.getAuth()); headers.forEach((name,value)->builder.header(name,resolver.resolve(value)));
        String method=operation.getMethod().toUpperCase(); String body=request.body()==null?"":request.body(); HttpRequest.BodyPublisher publisher=body.isBlank()?HttpRequest.BodyPublishers.noBody():HttpRequest.BodyPublishers.ofString(body,StandardCharsets.UTF_8);
        if(!body.isBlank() && headers.keySet().stream().noneMatch(h->h.equalsIgnoreCase("Content-Type"))) builder.header("Content-Type","application/json"); builder.method(method,publisher);
        try { HttpResponse<String> response=httpClient.send(builder.build(),HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)); return new ApiInvocationResult(response.statusCode(),response.headers().map(),response.body()); }
        catch(InterruptedException e){Thread.currentThread().interrupt();throw new IllegalStateException("API call interrupted",e);} catch(Exception e){throw new IllegalStateException("API call failed: "+e.getMessage(),e);}
    }
    private void applyAuthentication(Map<String,String> headers, ApiCatalogProperties.Auth auth) {
        if(auth==null || auth.getType()==null || auth.getType()==ApiCatalogProperties.AuthType.NONE) return;
        String value=resolver.resolve(auth.getValue());
        switch(auth.getType()) { case BEARER -> headers.put(auth.getHeaderName(),"Bearer "+value); case API_KEY, RAW_HEADER -> headers.put(auth.getHeaderName(),value); case BASIC -> { String encoded=Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8)); headers.put(auth.getHeaderName(),"Basic "+encoded); } default -> { } }
    }
}
