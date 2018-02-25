#!/usr/bin/env bash

if [ "$1" == "" ]; then
    echo "Usage: generate_benchmark RouterClassName"
    exit 1
fi

tmpfile=$(mktemp)

echo "{" >> "$tmpfile"
echo "\"routerType\": \"$1\"" >> "$tmpfile"
echo "}" >> "$tmpfile"

cd bench-test-generator

groovy freemarker-cli.groovy -o ../src/main/java/io/slinkydeveloper/bench/"$1"Benchmark.java -t i_am_so_lazy_to_build_bench.ftl ../src/main/resources/paths.json "$tmpfile"

rm "$tmpfile"

echo "You can find the benchmark for $1 in src/main/java/io/slinkydeveloper/bench/$1Benchmark.java"