#!/bin/bash
sudo rm -rf store-master
git clone https://github.com/TeamPJ1/store-master.git
cd store-master
chmod a+x scripts/docker-compose/app-build-deploy.sh
sudo ./scripts/docker-compose/app-build-deploy.sh -n storestg  -s start