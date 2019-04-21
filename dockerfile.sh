#!/bin/bash
docker run -it --rm -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven mvn clean install
cp mall-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","/app.jar"]
