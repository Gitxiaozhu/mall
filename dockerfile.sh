#!/bin/bash
VOLUME /temp
ADD mall-0.0.1-SNAPSHOT.jar mall.jar
ENTRYPOINT ["java","-jar","/mall.jar"]
