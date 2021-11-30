#!/bin/bash
# set mode -x to print a trace of simple commands, for commands executed in shell
set -x
# set mode -e  exit if error has throwed
# set -e


echo "###### Deploy store app using docker-compose"
cd scripts/docker-compose
docker-compose up -d