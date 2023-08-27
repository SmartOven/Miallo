#!/bin/bash

ip="31.172.74.69"
sshpass -p "$REMOTE_PASSWORD" scp -r /Users/smartoven/IdeaProjects/Miallo/deploy/local/files/* root@$ip:/root/miallo/tmp/files
sshpass -p "$REMOTE_PASSWORD" scp -r /Users/smartoven/IdeaProjects/Miallo/deploy/remote/* root@$ip:/root/miallo/tmp/scripts
