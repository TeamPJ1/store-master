#!/bin/bash
while getopts n:s: flag
do
    case "${flag}" in
        n) namespace=${OPTARG};;
        s) state=${OPTARG};;
    esac
done
echo "Namespace: $namespace";
echo "State: $state";

echo "###### Build docker images for backend services"
mvn compile com.google.cloud.tools:jib-maven-plugin:2.3.0:dockerBuild
echo "###### Build docker image for store-app-ui"
docker build -t store-app-ui:0.0.1-SNAPSHOT ./store-app-ui

cd scripts/k8s
#kubectl apply -f ns.yml
# Services in Kubernetes expose their endpoint using a common DNS pattern. It looks like this:
# <Service Aame>.<Namespace Name>.svc.cluster.local

echo "###### Delete existed resource store app"
kubectl delete -f  eureka-server.yml -n $namespace
kubectl delete -f  config-server.yml -n $namespace
kubectl delete -f  cloud-gateway.yml -n $namespace
kubectl delete -f  inventory-service.yml -n $namespace
kubectl delete -f  store-app-ui.yml -n $namespace
kubectl delete -f  ingress.yml -n $namespace
kubectl delete namespace $namespace
# kubectl delete namespace $namespace  --force --grace-period=0  # force suppression
echo "###### Deploy store app using k8s"
kubectl create namespace $namespace
#kubectl apply -f  eureka-server.yml -n $namespace
kubectl create -f  eureka-server.yml -n $namespace
kubectl create -f  config-server.yml -n $namespace
kubectl create -f  cloud-gateway.yml -n $namespace
kubectl create -f  inventory-service.yml -n $namespace
kubectl create -f  store-app-ui.yml -n $namespace
kubectl create -f  ingress.yml -n $namespace
echo "###### Test store app"
curl -H "Host: storeapp.kub" http://localhost/