param([string]$InstallDir="C:\ProgramData\McpUniversalApi")
$ErrorActionPreference="Stop"; $serviceExe=Join-Path $InstallDir "mcp-universal-api.exe"; if(-not(Test-Path $serviceExe)){throw "Service executable not found: $serviceExe"}
Push-Location $InstallDir; try { .\mcp-universal-api.exe stop; .\mcp-universal-api.exe uninstall } finally { Pop-Location }
Write-Host "Service uninstalled. Application files were left in $InstallDir for inspection."
