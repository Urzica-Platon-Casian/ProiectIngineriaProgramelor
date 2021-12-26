#!/bin/sh
mvn clean package && docker build -t com.pos/ProiectPos .
docker rm -f ProiectPos || true && docker run -d -p 9080:9080 -p 9443:9443 --name ProiectPos com.pos/ProiectPos