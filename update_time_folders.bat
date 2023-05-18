
set "targetDirectory=%CD%"

for /d /r "%targetDirectory%" %%F in (*) do (
    set "folderPath=%%F"
    setlocal enabledelayedexpansion
    for %%A in ("!folderPath!") do (
        powershell -Command "$dateTime = Get-Date -Year 2023 -Month 5 -Day 15 -Hour 8 -Minute (Get-Random -Minimum 3 -Maximum 8); Set-ItemProperty -Path '%%~A' -Name CreationTime -Value $dateTime; Set-ItemProperty -Path '%%~A' -Name LastWriteTime -Value $dateTime"
    )
    endlocal
)

echo Last modified and created time updated to a random time between 08:03 and 08:07 on May 15, 2023, for all folders in the current directory (%targetDirectory%).
