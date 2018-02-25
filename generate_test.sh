#!/usr/bin/env bash

if [ "$1" == "" ]; then
    echo "Usage: generate_test RouterClassName"
    exit 1
fi

tmpfile=$(mktemp)

echo "{" >> "$tmpfile"
echo "\"routerType\": \"$1\"" >> "$tmpfile"
echo "}" >> "$tmpfile"

mkdir -p src/test/java/io/slinkydeveloper/bench/

cd bench-test-generator

groovy freemarker-cli.groovy -o ../src/test/java/io/slinkydeveloper/bench/"$1"Test.java -t i_am_so_lazy_to_build_test.ftl ../src/main/resources/paths.json "$tmpfile"

rm "$tmpfile"

echo "You can find the test for $1 in src/test/java/io/slinkydeveloper/bench/$1Test.java"