#!/bin/bash

mkdir -p ~/logs/miallo
rm ~/logs/miallo/backend.log > /dev/null
java -jar ~/build/MialloBackend/backend.jar > ~/logs/miallo/backend.log
