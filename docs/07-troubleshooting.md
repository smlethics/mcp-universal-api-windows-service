# Troubleshooting

## Server does not start
Check Java 21, port 8090 availability and the application logs.

## MCP client cannot connect
Confirm the health endpoint first, then confirm the client is using Streamable HTTP and `/mcp`.

## Environment variable error
The configured secret variable is missing. Set it for the service account or at machine level and restart the service.

## Unknown API or operation
Use `listApis` and `describeApi` and compare the result with `application.yml`.

## Windows service fails
Check `JAVA_HOME`, the JAR path, WinSW presence, Administrator rights and files under `C:\ProgramData\McpUniversalApi\logs`.
