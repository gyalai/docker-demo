#!/bin/bash

mongod --dbpath /dev-data/db &
PID=$!

sleep 5

mongoimport -db demo --collection TECHNOLOGY --file /tmp/import.json --jsonArray

sleep 5

kill -1 $PID

sleep 10