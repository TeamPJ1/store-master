#!/bin/bash
# set mode -x to print a trace of simple commands, for commands executed in shell
set -x
# set mode -e  exit if error has throwed
# set -e

version="0.0.1-SNAPSHOT"


CI_REGISTRY_USER="sedokray"
CI_REGISTRY_PASSWORD="medsaid1133"
CI_REGISTRY="https://hub.docker.com"
CI_REPO="sedokray"

echo "###### Push docker image to docker registry"
echo "************ STEP1 :Connect to docker registry"
docker login -u $CI_REGISTRY_USER -p $CI_REGISTRY_PASSWORD
for image in eureka-server config-server gateway-proxy inventory-service store-app-ui
do
  echo "************ Tag docker image: $CI_REPO/$image:$version"
  docker tag "$image:$version" "$CI_REPO/$image:$version"
  echo "************ Push docker image: $CI_REPO/$image:$version"
  docker push "$CI_REPO/$image:$version"
done

