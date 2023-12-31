#!/bin/bash

MIALLO_SECRET_ID=e6qsr29fqbdif95i1djm
PROPERTIES_FILE=lockbox.properties

kill -15 $(cat miallo-backend.pid)
java -jar properties-fetcher.jar "$YC_OAUTH_TOKEN" "$MIALLO_SECRET_ID" "$PROPERTIES_FILE" > properties-fetcher.log
java -jar miallo.jar --spring.config.import="$PROPERTIES_FILE" > logs.log &
echo $! > miallo-backend.pid
