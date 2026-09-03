# What is MCP?

The Model Context Protocol (MCP) standardizes how an AI host discovers and uses external capabilities and context. A useful mental model is USB-C for AI integrations: an MCP client speaks one protocol to one or more MCP servers, while each server adapts real APIs, databases, applications or services.

## Actors

`User -> AI Host -> MCP Client -> MCP Server -> External System`

The host owns the user experience. The client speaks MCP. The server exposes selected capabilities and acts as a security/integration boundary. The external system remains the real REST, GraphQL, SOAP or vendor API.

## Capabilities

MCP servers can expose Tools, Resources and Prompts. This reference project deliberately enables Tools only because its purpose is controlled API integration.

## Transport

This project uses Streamable HTTP at `/mcp`. STDIO is useful when a host launches a server as a child process; Streamable HTTP is a natural fit for a continuously running Windows Service.

## MCP does not replace APIs

MCP standardizes the AI-facing contract. REST, GraphQL, SOAP and SDKs remain the downstream integration contracts.

## Discovery flow

1. `listApis`
2. `describeApi(api)`
3. select an operation
4. `invokeApi(...)`

Secrets are never tool parameters; they stay server-side in environment variables.
