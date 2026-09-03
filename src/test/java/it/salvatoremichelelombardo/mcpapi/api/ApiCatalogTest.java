package it.salvatoremichelelombardo.mcpapi.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import it.salvatoremichelelombardo.mcpapi.config.ApiCatalogProperties;
import org.junit.jupiter.api.Test;

class ApiCatalogTest {
    @Test void resolvesConfiguredOperation() {
        var properties=new ApiCatalogProperties(); var api=new ApiCatalogProperties.ApiDefinition(); var op=new ApiCatalogProperties.Operation(); op.setMethod("GET"); op.setPath("/items/{id}"); api.getOperations().put("get-item",op); properties.getApis().put("demo",api);
        var catalog=new ApiCatalog(properties); assertThat(catalog.operation("demo","get-item").getPath()).isEqualTo("/items/{id}");
    }
    @Test void rejectsUnknownApi() { var catalog=new ApiCatalog(new ApiCatalogProperties()); assertThatThrownBy(() -> catalog.api("missing")).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Unknown API"); }
}
