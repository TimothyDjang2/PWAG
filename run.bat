@echo off
javac -d bin -sourcepath src src\Core.java
pause
java -cp bin Core