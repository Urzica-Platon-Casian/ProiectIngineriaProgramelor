@echo off
call mvn clean package
call docker build -t com.pos/ProiectPos .
call docker rm -f ProiectPos
call docker run -d -p 9080:9080 -p 9443:9443 --name ProiectPos com.pos/ProiectPos