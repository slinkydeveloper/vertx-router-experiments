#!/usr/bin/env bash

mkdir -p src/test/java/io/slinkydeveloper/bench/

cd bench-test-generator

groovy freemarker-cli.groovy -o ../src/test/java/io/slinkydeveloper/bench/BaseRouterTest.java -t i_am_so_lazy_to_build_test.ftl ../src/main/resources/paths.json

echo "You can find the base test in src/test/java/io/slinkydeveloper/bench/BaseRouterTest.java"