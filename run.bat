@echo off
rmdir /s bin\pwag /q
javac -d bin -sourcepath src src\pwag\Core.java
pause
java -cp bin pwag.Core