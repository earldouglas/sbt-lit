// meta
organization := "com.earldouglas"
name := "sbt-lit"

// build
scalaVersion := "2.12.8"
scalacOptions ++= Seq( "-deprecation"
                     , "-feature"
                     , "-unchecked"
                     , "-Ywarn-unused"
                     , "-Ywarn-unused-import"
                     )
sbtPlugin := true
libraryDependencies += "com.vladsch.flexmark" % "flexmark-all" % "0.40.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

// publish
publishMavenStyle := false
licenses += ("ISC", url("https://opensource.org/licenses/ISC"))

// scripted-plugin
scriptedBufferLog := false
