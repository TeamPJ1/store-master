#!/bin/bash
sudo rm -rf store-master
git clone https://github.com/TeamPJ1/store-master.git
cd store-master
chmod a+x scripts/k8s/app-build-deploy.sh
sudo ./scripts/k8s/app-build-deploy.sh -n storestg  -b true -s start -i 192.168.12.12