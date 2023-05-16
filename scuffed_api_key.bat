@echo off
setlocal enabledelayedexpansion

set "search=fsdufhsdufhsdufhsdufyhsduifhsduifhsduifhsdiufhsdui"
set "replace=%1"
set "input_file=src/main/java/io/github/haappi/HelloApplication.java"
set "output_file=src/main/java/io/github/haappi/HelloApplication.java"

(for /f "delims=" %%i in ('type "%input_file%" ^| findstr /n "^"') do (
    set "line=%%i"
    setlocal enabledelayedexpansion
    echo(!line:*:=! | findstr /r "^" >nul
    if !errorlevel! equ 0 (
        set "line=!line:%search%=%replace%!"
        echo(!line!
    )
    endlocal
)) > "%output_file%"

endlocal
