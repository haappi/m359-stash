@echo off
git pull https://github.com/haappi/schoolStuff/ restaurant-game
rmdir ".git" /s /q
del /f "hi.bat"