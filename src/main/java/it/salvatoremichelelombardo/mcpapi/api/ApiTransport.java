package it.salvatoremichelelombardo.mcpapi.api;

public interface ApiTransport {
    ApiInvocationResult invoke(ApiInvocationRequest request);
}
