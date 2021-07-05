#!/usr/bin/env bash

set -e

pushd `dirname $0`

function test() {
  TMP_SRC_DIR=`mktemp -d`
  cp -rf . $TMP_SRC_DIR

  pushd $TMP_SRC_DIR

  echo "scalaVersion := \"$SCALA_VERSION\"" > scalaVersion.sbt
  echo "sbt.version=$SBT_VERSION" > project/build.properties

  cat project/build.properties
  cat scalaVersion.sbt

  TMP_HOME_DIR=`mktemp -d`

  SBT_LAUNCH=https://repo1.maven.org/maven2/org/scala-sbt/sbt-launch/$SBT_VERSION/sbt-launch-$SBT_VERSION.jar

  curl -s -L $SBT_LAUNCH -o $TMP_HOME_DIR/sbt-launch-$SBT_VERSION.jar

  HOME=$TMP_HOME_DIR java -jar $TMP_HOME_DIR/sbt-launch-$SBT_VERSION.jar clean run

  popd
}

SBT_VERSION=1.0.0 SCALA_VERSION=2.10.2 test
SBT_VERSION=1.5.4 SCALA_VERSION=2.13.4 test
