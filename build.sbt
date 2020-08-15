// meta
organization := "com.earldouglas"
name := "sbt-lit"

// build
scalaVersion := "2.12.12"
scalacOptions ++= Seq( "-deprecation"
                     , "-encoding", "utf8"
                     , "-feature"
                     , "-language:existentials"
                     , "-language:experimental.macros"
                     , "-language:higherKinds"
                     , "-language:implicitConversions"
                     , "-unchecked"
                     , "-Xfatal-warnings"
                     , "-Xlint"
                     , "-Ypartial-unification"
                     , "-Yrangepos"
                     , "-Ywarn-unused"
                     , "-Ywarn-unused-import"
                     )
sbtPlugin := true
enablePlugins(SbtPlugin)
libraryDependencies += "com.vladsch.flexmark" % "flexmark-all" % "0.40.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

// publish
publishMavenStyle := false
licenses += ("ISC", url("https://opensource.org/licenses/ISC"))

// scripted-plugin
scriptedBufferLog := false
