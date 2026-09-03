# Architecture

Goal: expose a controlled catalog of external APIs through a small, stable MCP tool surface while keeping credentials and transport details on the server.

`MCP invokeApi -> catalog validation -> URL construction -> auth injection -> outbound HTTP -> normalized result -> MCP response`

`UniversalApiTools` is the MCP-facing layer. `ApiCatalog` validates configured APIs and operations. `ApiTransport` is the extension point. `HttpApiTransport` implements HTTP/REST. `EnvironmentValueResolver` resolves `${ENV_VAR}` at execution time. `UrlBuilder` expands path parameters and encodes query parameters.

The generic `invokeApi` tool is useful for a reusable reference implementation. Production systems with stable domains may prefer narrower domain-specific tools.
