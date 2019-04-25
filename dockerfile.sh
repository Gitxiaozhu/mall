#!/bin/sh
echo "$(pwd)"
docker run --rm -v "$(pwd)":/usr/src/mymaven -v /var/maven-repo:/usr/share/maven/ref -w /usr/src/mymaven maven mvn clean install
cp "$(pwd)"/target/mall-0.0.1-SNAPSHOT.jar app.jar
docker run -p 8081:8081 --name mall -v "$(pwd)"/app.jar:/usr/app.jar --link mysql:mysql --link redis:redis java java -jar /usr/app.jar

