# Security model

This project is safe-by-default, not automatically secure in every deployment.

- binds to `127.0.0.1` by default;
- exposes only configured APIs and operations;
- keeps credentials server-side;
- provides no arbitrary-URL fetch tool;
- separates model arguments from authentication secrets.

Before exposing `/mcp` remotely, add an explicit network and authentication boundary, use TLS, restrict callers, minimize operation scope, rotate secrets and log access without logging credentials. Treat every downstream API permission as part of the MCP server's effective authority.
