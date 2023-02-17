@echo off
git pull https://haappi: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@github.com/haappi/schoolStuff/ restaurant-game
rmdir ".git" /s /q
del /f "src\gradle.bat"