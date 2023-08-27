#!/bin/bash

mkdir -p /var/logs/miallo
rm /var/logs/miallo/backend.log > /dev/null
java -jar ~/build/MialloBackend/backend.jar > /var/logs/miallo/backend.log
