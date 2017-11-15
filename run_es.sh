#!/bin/bash

mvn clean compile assembly:single
if [ $? == 0 ]; then
    java -cp /Users/arunesh/github/neo-es/target/NeoES-1.0-SNAPSHOT-jar-with-dependencies.jar com.inceptai.neo.es.App
fi
