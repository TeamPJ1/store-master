#!/bin/bash
echo "###### Build image backend services"
mvn compile com.google.cloud.tools:jib-maven-plugin:2.3.0:dockerBuild
echo "###### Build image store-app-ui"
docker build -t store-app-ui:0.0.1-SNAPSHOT ./store-app-ui
echo "###### Deploy store app using docker-compose"
cd scripts/docker-compose
docker-compose up -d
# docker-compose down