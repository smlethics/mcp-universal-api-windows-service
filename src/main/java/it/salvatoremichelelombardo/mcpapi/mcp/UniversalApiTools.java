package it.salvatoremichelelombardo.mcpapi.mcp;

import it.salvatoremichelelombardo.mcpapi.api.ApiCatalog;
import it.salvatoremichelelombardo.mcpapi.api.ApiInvocationRequest;
import it.salvatoremichelelombardo.mcpapi.api.ApiTransport;
import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class UniversalApiTools {
    private final ApiCatalog catalog; private final ApiTransport transport;
    public UniversalApiTools(ApiCatalog catalog, ApiTransport transport) { this.catalog = catalog; this.transport = transport; }
    @Tool(description = "List every external API configured in this MCP server.") public Object listApis() { return catalog.listApis(); }
    @Tool(description = "List and describe the configured operations for one external API.") public Object describeApi(@ToolParam(description = "Configured API name", required = true) String api) { var result = new LinkedHashMap<String,Object>(); catalog.operations(api).forEach((name, operation) -> { var info = new LinkedHashMap<String,Object>(); info.put("description", operation.getDescription()); info.put("method", operation.getMethod()); info.put("path", operation.getPath()); result.put(name, info); }); return result; }
    @Tool(description = "Invoke one configured external HTTP API operation. Use describeApi first. Secrets stay server-side.") public Object invokeApi(@ToolParam(description="Configured API name", required=true) String api, @ToolParam(description="Configured operation name", required=true) String operation, @ToolParam(description="Path template values", required=false) Map<String,String> pathParameters, @ToolParam(description="URL query parameters", required=false) Map<String,String> queryParameters, @ToolParam(description="Additional non-secret HTTP headers", required=false) Map<String,String> headers, @ToolParam(description="Raw request body", required=false) String body) { return transport.invoke(new ApiInvocationRequest(api, operation, pathParameters, queryParameters, headers, body)); }
    @Tool(description = "Return the MCP server current local timestamp.") public String serverTime() { return OffsetDateTime.now().toString(); }
}
