#!/bin/bash

function setMavenOpts() {
	export MAVEN_OPTS='-Xms4096m -Xmx4096m'
}

function downgradeToJDK14() {
	export JAVA_HOME=`/usr/libexec/java_home -v 14`
}

function doCleanInstall() {
	mvn clean install
}

setMavenOpts
downgradeToJDK14

doCleanInstall
