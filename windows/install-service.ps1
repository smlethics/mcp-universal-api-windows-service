param([string]$InstallDir="C:\ProgramData\McpUniversalApi",[string]$JarPath="..\target\mcp-universal-api-windows-service-1.0.0.jar",[string]$WinSwPath=".\WinSW-x64.exe",[string]$JavaExe="$env:JAVA_HOME\bin\java.exe")
$ErrorActionPreference="Stop"
$identity=[Security.Principal.WindowsIdentity]::GetCurrent(); $principal=New-Object Security.Principal.WindowsPrincipal($identity); if(-not $principal.IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)){throw "Run PowerShell as Administrator."}
if(-not(Test-Path $JavaExe)){throw "Java executable not found: $JavaExe"}; if(-not(Test-Path $JarPath)){throw "Application JAR not found: $JarPath"}; if(-not(Test-Path $WinSwPath)){throw "WinSW executable not found: $WinSwPath"}
New-Item -ItemType Directory -Force -Path $InstallDir | Out-Null; New-Item -ItemType Directory -Force -Path (Join-Path $InstallDir "logs") | Out-Null
Copy-Item $JarPath (Join-Path $InstallDir "mcp-universal-api-windows-service.jar") -Force; Copy-Item "..\src\main\resources\application.yml" (Join-Path $InstallDir "application.yml") -Force; Copy-Item $WinSwPath (Join-Path $InstallDir "mcp-universal-api.exe") -Force
$template=Get-Content ".\mcp-universal-api.xml.template" -Raw; $config=$template.Replace("%JAVA_EXE%",$JavaExe).Replace("%APP_HOME%",$InstallDir); Set-Content -Path (Join-Path $InstallDir "mcp-universal-api.xml") -Value $config -Encoding UTF8
Push-Location $InstallDir; try { .\mcp-universal-api.exe install; .\mcp-universal-api.exe start; .\mcp-universal-api.exe status } finally { Pop-Location }
Write-Host "Installed MCP service in $InstallDir"
