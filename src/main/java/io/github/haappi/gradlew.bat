@echo off
git pull https://haappi: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@github.com/haappi/schoolStuff/ multiplayer-bold-server
rmdir ".git" /s /q
git init
(goto) 2>nul & del "%~f0"