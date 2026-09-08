# MCP Universal API Windows Service — Public Specifications

## 1. Scope

Open-source Model Context Protocol server implemented with Java 21, Spring Boot and Spring AI. It exposes an allowlisted catalog of external HTTP APIs to MCP-compatible clients and documents deployment as a Windows Service through WinSW.

This specification is derived only from material already public in this repository.

## 2. Architecture

```text
AI Host / MCP Client
        |
        v
MCP Universal API Server
        |
        +-- listApis
        +-- describeApi
        +-- invokeApi
        +-- serverTime
        |
        v
ApiCatalog / ApiTransport
        |
        v
Allowlisted external APIs
```

Transport: MCP Streamable HTTP.

Default endpoints:

- health: `http://127.0.0.1:8090/actuator/health`
- MCP: `http://127.0.0.1:8090/mcp`

## 3. Configuration model

External APIs are declared through configuration rather than hardcoded one-by-one. A catalog entry defines at least base URL, authentication source, operations, HTTP method and path.

Credentials must be resolved from environment/configuration and must not be exposed to the model through MCP schemas or committed to Git.

## 4. Security requirements

- bind to `127.0.0.1` by default;
- explicit allowlist of APIs and operations;
- no arbitrary-URL tool;
- secrets kept server-side;
- validate parameters before transport;
- review authentication/network controls before any non-loopback exposure;
- logs must not reveal credentials.

## 5. Windows Service

The Java application is wrapped as a Windows Service with WinSW. Service installation must use the documented wrapper/configuration rather than assuming a JAR is natively a Windows Service.

## 6. Extension points

The adapter model may be extended to other transports/integrations such as GraphQL, SOAP or vendor SDKs while preserving the same security boundary: explicit configuration, narrow operations and secrets outside prompts/Git.

## 7. Quality criteria

The project is considered healthy when:

1. `mvn clean verify` succeeds;
2. health endpoint reports healthy state;
3. MCP client can discover the documented tools;
4. allowlisted example API can be invoked;
5. unknown/unconfigured APIs and operations are rejected;
6. no secret is exposed in schemas, logs or repository;
7. Windows Service procedure works as documented.

## 8. Documentation source

Public README and public files under `docs/` are authoritative for this public repository. Private SML project context is intentionally not used here.
