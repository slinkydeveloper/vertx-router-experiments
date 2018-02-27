#!/usr/bin/env bash

mvn clean package

java -jar target/benchmarks.jar -rf json -rff output.json

cp output.json chart-generator

mkdir out

cd chart-generator

npm install

node index.js

rm output.json