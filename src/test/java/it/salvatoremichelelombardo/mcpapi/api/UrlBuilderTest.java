package it.salvatoremichelelombardo.mcpapi.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.Map;
import org.junit.jupiter.api.Test;

class UrlBuilderTest {
    private final UrlBuilder builder = new UrlBuilder();
    @Test void buildsPathAndQueryParameters() {
        String result = builder.build("https://api.example.com/", "/users/{id}", Map.of("id","A B"), Map.of("page","1","q","hello world"));
        assertThat(result).startsWith("https://api.example.com/users/A%20B?").contains("page=1").contains("q=hello+world");
    }
    @Test void rejectsMissingPathParameter() {
        assertThatThrownBy(() -> builder.build("https://api.example.com", "/users/{id}", Map.of(), Map.of())).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Missing path parameter");
    }
}
