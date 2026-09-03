package it.salvatoremichelelombardo.mcpapi.config;

import it.salvatoremichelelombardo.mcpapi.mcp.UniversalApiTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpToolConfiguration {
    @Bean
    ToolCallbackProvider universalApiToolProvider(UniversalApiTools tools) {
        return MethodToolCallbackProvider.builder().toolObjects(tools).build();
    }
}
