#!/bin/bash
# set mode -x to print a trace of simple commands, for commands executed in shell
set -x
# set mode -e  exit if error has throwed
# set -e

version="0.0.1-SNAPSHOT"

echo "###### Build docker images for backend services"
mvn compile com.google.cloud.tools:jib-maven-plugin:2.3.0:dockerBuild
echo "###### Build docker image for store-app-ui"
docker build -t store-app-ui:0.0.1-SNAPSHOT ./store-app-ui

