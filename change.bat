@echo off
set /p input= Enter new project name:

ren "src\main\java\resources\io\github\haappi\template" %input%
ren "src\main\java\io\github\haappi\template" %input%
