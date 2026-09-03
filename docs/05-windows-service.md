# Windows Service

A Java JAR is not a native Windows Service. This repository uses WinSW as the service wrapper.

## Build

```powershell
mvn clean verify
```

Download `WinSW-x64.exe` from the official WinSW project and place it in `windows/`. Then open PowerShell as Administrator:

```powershell
cd windows
.\install-service.ps1
```

Default installation directory: `C:\ProgramData\McpUniversalApi`.

Useful commands:

```powershell
.\service-status.ps1
.\uninstall-service.ps1
```

The service starts automatically, restarts after failure and writes rolling logs under the installation directory.
