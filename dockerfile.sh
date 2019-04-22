#!/bin/bash
docker run -it --rm -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven --link mysql:mysql --link redis:redis maven mvn clean install
cp /var/jenkins_home/workspace/mall/target/mall-0.0.1-SNAPSHOT.jar app.jar
docker run -d -p 8081:8081 --name springboot java java -jar /"$(pwd)"/app.jar
