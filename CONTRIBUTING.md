# Contributing

Contributions are welcome.

Please keep the reference project focused, dependency-light and safe by default. New integrations should preserve:

- allowlisted operations;
- server-side secrets;
- local-only network binding by default;
- tests for URL/configuration behavior;
- documentation for new configuration fields.

Run `mvn clean verify` before submitting changes.
