#!/bin/bash
docker run -it --rm -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven mvn clean install
VOLUME /tmp
add mall-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
