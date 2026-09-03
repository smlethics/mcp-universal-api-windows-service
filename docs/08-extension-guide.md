# Extension guide

`ApiTransport` is intentionally small so other transports can be introduced without changing the MCP-facing contract.

For GraphQL, implement a transport that targets a configured endpoint and serializes query/variables. For SOAP, add XML body handling, SOAPAction and response normalization. For proprietary SDKs, create an adapter that maps configured operations to SDK calls.

A future OpenAPI extension could generate catalog operations from an OpenAPI document, but production use should still apply an explicit allowlist rather than exposing every discovered operation automatically.
