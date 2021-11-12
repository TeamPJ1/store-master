#!/bin/bash
echo "###### Build image backend services"
mvn compile com.google.cloud.tools:jib-maven-plugin:2.3.0:dockerBuild
echo "###### Build image store-app-ui"
docker build -t store-app-ui:0.0.1-SNAPSHOT ./store-app-ui
echo "###### Deploy store app using docker-compose"
cd scripts/k8s
#kubectl apply -f ns.yml
# Services in Kubernetes expose their endpoint using a common DNS pattern. It looks like this:
# <Service Aame>.<Namespace Name>.svc.cluster.local

kubectl create namespace storestg
#kubectl apply -f  eureka-server.yml -n storestg
kubectl create -f  eureka-server.yml -n storestg
kubectl create -f  config-server.yml -n storestg
kubectl create -f  cloud-gateway.yml -n storestg
kubectl create -f  inventory-service.yml -n storestg
kubectl create -f  store-app-ui.yml -n storestg
kubectl create -f  ingress.yml -n storestg