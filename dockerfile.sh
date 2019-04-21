#!/bin/bash
docker run -it --rm -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven mvn clean install
cp mall-0.0.1-SNAPSHOT.jar app.jar
docker run -d -p 8081:8081 --name springboot --link mysql:mysql --link redis:redis java java -jar /"$(pwd)"/app.jar
