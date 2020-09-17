#!/bin/bash

function setMavenOpts() {
	export MAVEN_OPTS='-Xms4096m -Xmx4096m'
}

function setJavaHome() {
	export JAVA_HOME=`/usr/libexec/java_home -v 15`
}

function doCleanInstall() {
	mvn clean install
}

setMavenOpts
setJavaHome

doCleanInstall
