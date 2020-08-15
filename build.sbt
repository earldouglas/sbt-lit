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
crossSbtVersions := Seq("1.0.0")
sbtPlugin := true
enablePlugins(SbtPlugin)
libraryDependencies += "com.vladsch.flexmark" % "flexmark-all" % "0.62.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.1" % "test"

// publish
publishMavenStyle := false
licenses += ("ISC", url("https://opensource.org/licenses/ISC"))

// scripted-plugin
scriptedBufferLog := false
