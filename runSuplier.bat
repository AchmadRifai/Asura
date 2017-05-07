@echo off
cls
ver
cd Suplier
mvn clean install exec:java
cd ..