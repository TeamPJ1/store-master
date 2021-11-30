#!/bin/bash
# set mode -x to print a trace of simple commands, for commands executed in shell
set -x
# set mode -e  exit if error has throwed
# set -e
mvn -Dmaven.test.skip=true   org.sonarsource.scanner.maven:sonar-maven-plugin:4.6.1.2450:sonar