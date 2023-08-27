#!/bin/bash

rm -rf /Users/smartoven/IdeaProjects/Miallo/deploy/local/files/backend*
cd /Users/smartoven/IdeaProjects/Miallo/backend || (echo error-with-cd && exit)
./gradlew bootJar > /dev/null
cp /Users/smartoven/IdeaProjects/Miallo/backend/build/libs/backend-1.0.0.jar /Users/smartoven/IdeaProjects/Miallo/deploy/local/files/backend.jar
