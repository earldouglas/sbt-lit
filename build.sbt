// meta
organization := "com.earldouglas"
name := "sbt-lit"

// build
scalaVersion := "2.12.16"
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
libraryDependencies += "com.vladsch.flexmark" % "flexmark-all" % "0.62.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.13" % "test"

// scripted-plugin
scriptedBufferLog := false

// publish to Sonatype, https://www.scala-sbt.org/release/docs/Using-Sonatype.html
credentials := List(Credentials(Path.userHome / ".sbt" / "sonatype_credentials"))
description := "Literate programming with sbt"
developers := List(Developer(id = "earldouglas", name = "James Earl Douglas", email = "james@earldouglas.com", url = url("https://earldouglas.com/")))
homepage := Some(url("https://github.com/earldouglas/sbt-lit"))
licenses := List(("ISC", url("https://opensource.org/licenses/ISC")))
organizationHomepage := Some(url("https://earldouglas.com/"))
organizationName := "James Earl Douglas"
pomIncludeRepository := { _ => false }
publishMavenStyle := true
publishTo := Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
scmInfo := Some(ScmInfo(url("https://github.com/earldouglas/sbt-lit"), "scm:git@github.com:earldouglas/sbt-lit.git"))
