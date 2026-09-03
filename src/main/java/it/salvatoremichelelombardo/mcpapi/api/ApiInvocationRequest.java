package it.salvatoremichelelombardo.mcpapi.api;

import java.util.Map;

public record ApiInvocationRequest(
        String api,
        String operation,
        Map<String, String> pathParameters,
        Map<String, String> queryParameters,
        Map<String, String> headers,
        String body) {
}
