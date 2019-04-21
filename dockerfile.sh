#!/bin/bash
docker run --rm -v SpringbootTest3:/user/src/java -w /user/src/java maven mvn clean install
add mall-open-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
