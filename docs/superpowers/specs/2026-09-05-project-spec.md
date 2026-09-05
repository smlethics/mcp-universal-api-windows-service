# MCP Universal API Windows Service — Specifica canonica

**Repository:** `smlethics/mcp-universal-api-windows-service`  
**Visibilità:** PUBLIC  
**Data:** 2026-09-05

## Obiettivo

Server MCP open-source Java/Spring che espone un catalogo allow-listed di API HTTP esterne a client MCP compatibili e documenta l'esecuzione continua come Windows Service tramite WinSW.

## Architettura

- Java 21, Spring Boot, Spring AI.
- MCP Streamable HTTP.
- `ApiCatalog` per definizione delle integrazioni.
- `ApiTransport` per invocazioni HTTP controllate.
- configurazione dichiarativa di API/operazioni.
- Windows Service tramite WinSW.
- bind locale predefinito `127.0.0.1:8090`.

## Tool principali

- `listApis`
- `describeApi`
- `invokeApi`
- `serverTime`

## Vincoli di sicurezza

- allowlist esplicita di API e operazioni;
- nessun arbitrary-URL tool;
- credenziali risolte da variabili d'ambiente;
- secret mai inseriti in prompt, schemi MCP o repository;
- esposizione di rete non prevista senza protezioni aggiuntive.

## Estendibilità

Il modello può essere esteso a REST aggiuntive e, attraverso adapter dedicati, GraphQL, SOAP o vendor SDK, mantenendo lo stesso confine di sicurezza e configurazione.

## Criteri di verifica

1. `mvn clean verify` riuscito;
2. health locale disponibile;
3. MCP Streamable HTTP raggiungibile;
4. catalogo e descrizione API coerenti con la configurazione;
5. invocazioni limitate alle operazioni allow-listed;
6. installazione WinSW documentata e riproducibile;
7. nessun secret nel repository.

## Fonti della specifica

Specifica derivata esclusivamente da README e documentazione pubblica del repository. Nessun contenuto proveniente da chat o infrastruttura privata è stato aggiunto.
