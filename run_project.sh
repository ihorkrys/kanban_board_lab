#!/bin/bash

gradle build

docker-compose -f docker-compose.yaml build
docker-compose -f docker-compose.yaml up