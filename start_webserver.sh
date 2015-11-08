#!/bin/sh

#mvn clean install -DskipTests
java -Xmx300m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=. -cp target/javatools-examples-0.1-SNAPSHOT-jar-with-dependencies.jar no.bekk.javatools.WebappMain