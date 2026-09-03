package it.salvatoremichelelombardo.mcpapi.api;

import java.util.List;
import java.util.Map;

public record ApiInvocationResult(
        int status,
        Map<String, List<String>> headers,
        String body) {
}
