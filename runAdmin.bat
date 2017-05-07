@echo off
cls
ver
cd Admin
mvn clean install exec:java
cd ..