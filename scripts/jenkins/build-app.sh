#!/bin/bash
# set mode -x to print a trace of simple commands, for commands executed in shell
set -x
# set mode -e  exit if error has throwed
# set -e

mvn -Dmaven.test.failure.ignore=true clean package