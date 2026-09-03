package it.salvatoremichelelombardo.mcpapi.api;

import it.salvatoremichelelombardo.mcpapi.config.ApiCatalogProperties;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ApiCatalog {
    private final ApiCatalogProperties properties;
    public ApiCatalog(ApiCatalogProperties properties) { this.properties = properties; }
    public List<String> listApis() { return properties.getApis().keySet().stream().sorted().toList(); }
    public Map<String, ApiCatalogProperties.Operation> operations(String apiName) { return api(apiName).getOperations(); }
    public ApiCatalogProperties.ApiDefinition api(String apiName) { var api = properties.getApis().get(apiName); if (api == null) throw new IllegalArgumentException("Unknown API: " + apiName); return api; }
    public ApiCatalogProperties.Operation operation(String apiName, String operationName) { var operation = api(apiName).getOperations().get(operationName); if (operation == null) throw new IllegalArgumentException("Unknown operation: " + operationName); return operation; }
}
