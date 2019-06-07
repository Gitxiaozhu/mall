# #!/bin/sh
if [ -e app.jar ]; then
       docker stop mall
       docker rm mall
fi
cp -r "$(pwd)"/target/mall-0.0.1-SNAPSHOT.jar demo.jar
docker run -it --rm -v $PWD:/usr/src/java -w /usr/src/java maven mvn clean install -DarchetypeCatalog=internal
#docker run --rm -v "$(pwd)":/usr/src/mymaven -v /var/maven-repo:/usr/share/maven/ref -w /usr/src/mymaven maven mvn clean install -DarchetypeCatalog=internal
#docker run -it --rm -v "$PWD":/usr/src/mymaven -v "$HOME/.m2":/root/.m2 -v "$PWD/target:/usr/src/mymaven/target" -w /usr/src/mymaven maven mvn clean package -DarchetypeCatalog=internal
docker run -d -p 8081:8081 --name mall -v app.jar:/usr/app.jar --link mysql:mysql --link redis:redis java java -jar /usr/app.jar

