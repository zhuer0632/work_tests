@echo off  


set JAVA_HOME=./jdk
set path=;%JAVA_HOME%\bin;

java  -jar  target/packagetest-1.0-SNAPSHOT.jar

echo.
echo ��������˳�...

pause>nul