#!/bin/bash
docker run -it --rm -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven --link mysql:mysql --link redis:redis maven mvn clean install
cp "$(pwd)"/target/mall-0.0.1-SNAPSHOT.jar app.jar
docker run -p 8081:8081 --name mall -v "$(pwd)"/app.jar:/usr/app.jar java java -jar /usr/app.jar

