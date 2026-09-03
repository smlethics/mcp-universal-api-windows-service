package it.salvatoremichelelombardo.mcpapi.config;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api-catalog")
public class ApiCatalogProperties {
    private Map<String, ApiDefinition> apis = new LinkedHashMap<>();
    public Map<String, ApiDefinition> getApis() { return apis; }
    public void setApis(Map<String, ApiDefinition> apis) { this.apis = apis; }

    public static class ApiDefinition {
        private String baseUrl;
        private Auth auth = new Auth();
        private Map<String, Operation> operations = new LinkedHashMap<>();
        public String getBaseUrl() { return baseUrl; }
        public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }
        public Auth getAuth() { return auth; }
        public void setAuth(Auth auth) { this.auth = auth; }
        public Map<String, Operation> getOperations() { return operations; }
        public void setOperations(Map<String, Operation> operations) { this.operations = operations; }
    }

    public static class Auth {
        private AuthType type = AuthType.NONE;
        private String headerName = "Authorization";
        private String value;
        public AuthType getType() { return type; }
        public void setType(AuthType type) { this.type = type; }
        public String getHeaderName() { return headerName; }
        public void setHeaderName(String headerName) { this.headerName = headerName; }
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }

    public enum AuthType { NONE, BEARER, API_KEY, BASIC, RAW_HEADER }

    public static class Operation {
        private String description;
        private String method = "GET";
        private String path = "/";
        private Map<String, String> headers = new LinkedHashMap<>();
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getMethod() { return method; }
        public void setMethod(String method) { this.method = method; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public Map<String, String> getHeaders() { return headers; }
        public void setHeaders(Map<String, String> headers) { this.headers = headers; }
    }
}
