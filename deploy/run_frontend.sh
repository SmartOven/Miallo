#!/bin/bash

cd ~/Miallo/frontend
npm i > ~/logs/miallo/frontend-build.log
npm run build > ~/logs/miallo/frontend.log
