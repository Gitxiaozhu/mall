#!/bin/bash
mvn package docker:build
VOLUME /temp
ADD mall-0.0.1-SNAPSHOT.jar mall.jar
ENTRYPOINT ["java","-jar","/mall.jar"]
