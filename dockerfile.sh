#!/bin/sh
if [ -e app.jar ]; then
       docker stop mall
       docker rm mall
fi
docker run -it --rm -v "$PWD":/usr/src/mymaven -v "$HOME/.m2":/root/.m2 -v "$PWD/target:/usr/src/mymaven/target" -w /usr/src/mymaven maven mvn clean package -DarchetypeCatalog=internal
docker run -d -p 8081:8081 --name mall -v "$(pwd)"/target/mall-0.0.1-SNAPSHOT.jar:/usr/app.jar --link mysql:mysql --link redis:redis java java -jar /usr/app.jar
