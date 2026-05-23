#!/bin/bash
if [ ! -f junit-4.13.2.jar ]; then
    wget -q https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar
fi
if [ ! -f hamcrest-core-1.3.jar ]; then
    wget -q https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
fi
javac -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar *.java
java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore GameUtilsTest
