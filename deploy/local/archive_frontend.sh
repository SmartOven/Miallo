#!/bin/bash

rm -rf /Users/smartoven/IdeaProjects/Miallo/deploy/local/files/frontend*
rm -rf /Users/smartoven/IdeaProjects/Miallo/frontend/node_modules
cd /Users/smartoven/IdeaProjects/Miallo/frontend || (echo error-with-cd && exit)
tar -czf frontend.tar.gz * > /dev/null
mv frontend.tar.gz /Users/smartoven/IdeaProjects/Miallo/deploy/local/files/frontend.tar.gz
