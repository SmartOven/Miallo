#!/bin/bash

rm -rf ~/build/MialloBackend
mkdir -p ~/build/MialloBackend
cd ~/Miallo/backend
./gradlew bootJar > /dev/null
cp ~/Miallo/backend/build/libs/backend-1.0.0.jar ~/build/MialloBackend/backend.jar
