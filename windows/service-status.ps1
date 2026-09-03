param([string]$InstallDir="C:\ProgramData\McpUniversalApi")
& (Join-Path $InstallDir "mcp-universal-api.exe") status
try { Invoke-RestMethod "http://127.0.0.1:8090/actuator/health" | ConvertTo-Json -Depth 5 } catch { Write-Warning "Health endpoint unavailable: $($_.Exception.Message)" }
