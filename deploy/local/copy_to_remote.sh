#!/bin/bash

ip="31.172.74.69"
scp -r /Users/smartoven/IdeaProjects/Miallo/deploy/local/files/* root@$ip:/root/miallo/files > /dev/null
scp -r /Users/smartoven/IdeaProjects/Miallo/deploy/remote/* root@$ip:/root/miallo/scripts > /dev/null
