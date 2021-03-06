#!/bin/bash
# set mode -x to print a trace of simple commands, for commands executed in shell
set -x
# set mode -e  exit if error has throwed
# set -e

version="0.0.1-SNAPSHOT"
while getopts n:s:b:i:v: flag
do
    case "${flag}" in
        n) namespace=${OPTARG};;
        s) state=${OPTARG};;
        b) build=${OPTARG};;
        i) ipaddress=${OPTARG};;
        v) version=${OPTARG};;
    esac
done

echo "###########################Params##############################"
echo "Namespace: "$namespace
echo "State: $state"
echo "Build: $build"
echo "Ip address: $ipaddress"
echo "Version: $version"
echo "###############################################################"

# if [ -z "$namespace" ]   équivalent à [ ! -n "$namespace" ]
if [ -z "$namespace" ]
then
  echo "This script requires a namespace argument input. None found. Exiting."
  exit 1
fi


pushRepo=false
if [[ "$build" == "true" ]]
then
  echo "###### Build docker images for backend services"
  mvn compile com.google.cloud.tools:jib-maven-plugin:2.3.0:dockerBuild
  echo "###### Build docker image for store-app-ui"
  docker build -t store-app-ui:0.0.1-SNAPSHOT ./store-app-ui
  pushRepo=true
fi

CI_REGISTRY_USER="sedokray"
CI_REGISTRY_PASSWORD="medsaid1133"
CI_REGISTRY="https://hub.docker.com"
CI_REPO="sedokray"

echo "###### Push docker image to docker registry"
if [[ "$pushRepo" == "true" ]]
then
  echo "************ STEP1 :Connect to docker registry"
  docker login -u $CI_REGISTRY_USER -p $CI_REGISTRY_PASSWORD
  for image in eureka-server config-server gateway-proxy inventory-service store-app-ui
  do
    echo "************ Tag docker image: $CI_REPO/$image:$version"
    docker tag "$image:$version" "$CI_REPO/$image:$version"
    echo "************ Push docker image: $CI_REPO/$image:$version"
    docker push "$CI_REPO/$image:$version"
  done
fi

cd scripts/k8s
#kubectl apply -f ns.yml
# Services in Kubernetes expose their endpoint using a common DNS pattern. It looks like this:
# <Service Aame>.<Namespace Name>.svc.cluster.local

echo "###### Delete existed resource store app"
#kubectl delete -f  ingress.yml -n $namespace
kubectl delete -f  store-app-ui.yml -n $namespace
kubectl delete -f  cloud-gateway.yml -n $namespace
kubectl delete -f  eureka-server.yml -n $namespace
kubectl delete -f  config-server.yml -n $namespace
kubectl delete -f  inventory-service.yml -n $namespace

kubectl get namespace $namespace -o json > $namespace.json
sed -i -e 's/"kubernetes"//' $namespace.json
kubectl replace --raw "/api/v1/namespaces/$namespace/finalize" -f ./$namespace.json

# kubectl delete namespace $namespace  --force --grace-period=0  # force suppression
echo "###### Deploy store app using k8s"
kubectl create namespace $namespace
#kubectl apply -f  eureka-server.yml -n $namespace
kubectl create -f  eureka-server.yml -n $namespace
kubectl create -f  config-server.yml -n $namespace
kubectl create -f  cloud-gateway.yml -n $namespace
kubectl create -f  inventory-service.yml -n $namespace
kubectl create -f  store-app-ui.yml -n $namespace
#kubectl create -f  ingress.yml -n $namespace
echo "###### Test store app"
curl -H "Host: web.store.kub" $ipaddress
curl  $ipaddress/store
echo "###### Check resources"
echo "kubectl get all -n $namespace"
kubectl get all -n $namespace