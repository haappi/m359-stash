

set "targetDirectory=%CD%"

for /r "%targetDirectory%" %%F in (*) do (
    powershell -Command "$dateTime = Get-Date -Year 2023 -Month 5 -Day 15 -Hour 8 -Minute (Get-Random -Minimum 3 -Maximum 8); $path = '%%F'; if ((Get-Item -LiteralPath $path).PSIsContainer) { $item = Get-Item -LiteralPath $path; $item.CreationTime = $dateTime; $item.LastWriteTime = $dateTime; $item.LastAccessTime = $dateTime; $item | Out-Null } else { $file = New-Object -TypeName System.IO.FileInfo -ArgumentList $path; $file.CreationTime = $dateTime; $file.LastWriteTime = $dateTime; $file.LastAccessTime = $dateTime; $file | Out-Null }"
)

echo Last accessed and file modified time updated to a random time between 08:03 and 08:07 on May
