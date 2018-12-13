#!/usr/bin/env bash
docker rm -f mongo

docker run -it --name mongo --rm --publish 27017:27017 mongo:3.6
