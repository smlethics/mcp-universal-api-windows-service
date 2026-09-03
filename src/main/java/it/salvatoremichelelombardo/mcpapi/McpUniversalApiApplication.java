package it.salvatoremichelelombardo.mcpapi;

import it.salvatoremichelelombardo.mcpapi.config.ApiCatalogProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApiCatalogProperties.class)
public class McpUniversalApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(McpUniversalApiApplication.class, args);
    }
}
