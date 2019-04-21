#!/bin/bash
docker run --rm -v /mall/:/java -w /java maven mvn clean install
add mall-open-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
