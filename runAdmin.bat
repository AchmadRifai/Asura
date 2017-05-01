@echo off
cls
cd Admin
mvn clean install exec:java
cd ..