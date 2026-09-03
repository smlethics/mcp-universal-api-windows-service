# API configuration

External APIs are defined under `api-catalog.apis` in `application.yml`.

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

Supported auth modes: `NONE`, `BEARER`, `API_KEY`, `BASIC`, `RAW_HEADER`. Put real credentials in environment variables, never in Git. Path placeholders are supplied through `pathParameters`; query values through `queryParameters`; request bodies are passed as raw strings, normally JSON.
