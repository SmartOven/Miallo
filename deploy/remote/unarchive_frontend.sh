#!/bin/bash

cd tmp || (echo error-with-cd && exit)
tar -xzvf /Users/smartoven/IdeaProjects/Miallo/deploy/local/files/frontend.tar.gz > /dev/null
