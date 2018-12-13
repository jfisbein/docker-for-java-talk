#!/usr/bin/env bash

docker run -it -e spring.data.mongodb.uri="mongodb://172.16.24.155/test" quotes-backend
