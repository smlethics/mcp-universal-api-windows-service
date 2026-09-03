package it.salvatoremichelelombardo.mcpapi.api;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class UrlBuilder {
    public String build(String baseUrl, String pathTemplate, Map<String,String> pathParameters, Map<String,String> queryParameters) {
        String path = pathTemplate;
        if (pathParameters != null) for (var entry : pathParameters.entrySet()) path = path.replace("{" + entry.getKey() + "}", encodePath(entry.getValue()));
        if (path.matches(".*\\{[^}]+}.*")) throw new IllegalArgumentException("Missing path parameter for template: " + pathTemplate);
        StringBuilder url = new StringBuilder(stripTrailingSlash(baseUrl)); if (!path.startsWith("/")) url.append('/'); url.append(path);
        if (queryParameters != null && !queryParameters.isEmpty()) { boolean first = !url.toString().contains("?"); for (var entry : queryParameters.entrySet()) { url.append(first ? '?' : '&'); first = false; url.append(encodeQuery(entry.getKey())).append('=').append(encodeQuery(entry.getValue())); } }
        return url.toString();
    }
    private String stripTrailingSlash(String value) { return value.endsWith("/") ? value.substring(0, value.length() - 1) : value; }
    private String encodePath(String value) { return URLEncoder.encode(value, StandardCharsets.UTF_8).replace("+", "%20"); }
    private String encodeQuery(String value) { return URLEncoder.encode(value, StandardCharsets.UTF_8); }
}
