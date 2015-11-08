#!/bin/sh

#mvn clean install -DskipTests
java -cp target/javatools-examples-0.1-SNAPSHOT-jar-with-dependencies.jar no.bekk.javatools.HttpClientMain $@