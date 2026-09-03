# MCP Universal API Windows Service

[![build](https://github.com/smlethics/mcp-universal-api-windows-service/actions/workflows/build.yml/badge.svg)](https://github.com/smlethics/mcp-universal-api-windows-service/actions/workflows/build.yml)

A reference **Model Context Protocol (MCP) server** built with **Java 21, Spring Boot and Spring AI**. It exposes an allowlisted catalog of external HTTP APIs to MCP-compatible AI clients and includes a documented Windows Service installation using WinSW.

> The goal is not merely to show an MCP “hello world”. The project demonstrates the boundary between an AI-facing MCP contract and real external APIs, with secrets kept server-side and integrations defined by configuration.

## What you learn

- what MCP is and what problem it solves;
- host/client/server architecture;
- MCP tools and Streamable HTTP;
- how to expose REST APIs without writing one integration class per endpoint;
- how to keep credentials out of prompts and Git;
- how to run the MCP server continuously as a Windows Service;
- how to extend the adapter model for GraphQL, SOAP and vendor SDKs.

## Architecture

```text
User
  |
AI Host
  |
MCP Client
  |  Model Context Protocol / Streamable HTTP
  v
+-------------------------------------+
| MCP Universal API Server            |
|                                     |
| listApis                            |
| describeApi                         |
| invokeApi                           |
| serverTime                          |
|          |                          |
|     ApiCatalog                      |
|          |                          |
|     ApiTransport                    |
+----------|--------------------------+
           |
           | HTTP / REST / adapters
           v
   External APIs / services
```

## Quick start

Requirements: Java 21 and Maven 3.9+.

```bash
mvn clean verify
mvn spring-boot:run
```

Health:

```text
http://127.0.0.1:8090/actuator/health
```

MCP Streamable HTTP endpoint:

```text
http://127.0.0.1:8090/mcp
```

The bundled configuration contains a harmless JSONPlaceholder example, so the project can be explored without an API key.

## Configure your own API

```yaml
api-catalog:
  apis:
    my-api:
      base-url: https://api.example.com
      auth:
        type: BEARER
        value: ${MY_API_TOKEN}
      operations:
        get-user:
          description: Read one user.
          method: GET
          path: /v1/users/{id}
```

The MCP client can then discover `my-api`, inspect `get-user`, and invoke it. The model never receives `MY_API_TOKEN`.

See [API configuration](docs/03-api-configuration.md).

## MCP theory

Start with [What is MCP?](docs/01-what-is-mcp.md). The short version:

**MCP standardizes the connection between an AI application and external capabilities. It does not replace REST, GraphQL, SOAP or vendor APIs; an MCP server adapts those systems into a protocol an AI client can discover and call consistently.**

## Windows Service

A Java JAR is not itself a Windows Service. This repository uses WinSW as the Windows Service wrapper.

See [Windows Service guide](docs/05-windows-service.md).

## Security

The project deliberately:

- binds to `127.0.0.1` by default;
- allowlists APIs and operations in configuration;
- resolves credentials from environment variables;
- does not provide an arbitrary-URL tool;
- does not put secrets in MCP schemas.

Read [Security model](docs/04-security.md) before exposing the endpoint over a network.

## Documentation

1. [What is MCP?](docs/01-what-is-mcp.md)
2. [Architecture](docs/02-architecture.md)
3. [API configuration](docs/03-api-configuration.md)
4. [Security model](docs/04-security.md)
5. [Windows Service](docs/05-windows-service.md)
6. [Client connection](docs/06-client-connection.md)
7. [Troubleshooting](docs/07-troubleshooting.md)
8. [Extension guide](docs/08-extension-guide.md)

## Repository topics

Recommended GitHub topics:

`mcp`, `model-context-protocol`, `spring-ai`, `spring-boot`, `java`, `java21`, `windows-service`, `winsw`, `powershell`, `rest-api`, `ai-agents`, `mcp-server`, `open-source`

## Author

Designed and developed by **Salvatore Michele Lombardo** as a practical reference implementation for developers exploring MCP integrations, API boundaries and reliable Windows deployment. The project is shared openly so that others can study it, adapt it and improve it.

## License

MIT.
