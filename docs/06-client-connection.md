# Client connection

Start the application or Windows Service, verify `http://127.0.0.1:8090/actuator/health`, then configure an MCP-compatible client to use Streamable HTTP at `http://127.0.0.1:8090/mcp`.

A simple functional sequence is: call `serverTime`, then `listApis`, then `describeApi("jsonplaceholder")`, and finally invoke a configured operation.

Client configuration syntax varies by host; use the host's current MCP documentation rather than copying settings intended for another client.
