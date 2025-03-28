[![Build status](https://github.com/earldouglas/sbt-lit/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/earldouglas/sbt-lit/actions/workflows/build.yml)
[![Latest version](https://img.shields.io/github/v/tag/earldouglas/sbt-lit)](https://github.com/earldouglas/sbt-lit/tags)
[![Maven Central](https://img.shields.io/maven-central/v/com.earldouglas/sbt-lit_2.12_1.0)](https://repo1.maven.org/maven2/com/earldouglas/sbt-lit_2.12_1.0/)

## Requirements

* sbt 1.1.0+
* Scala 2.10.2+

## Getting started

Add the sbt-lit plugin to your project:

*project/plugins.sbt:*

```scala
addSbtPlugin("com.earldouglas" % "sbt-lit" % "0.0.6")
```

Write some literate code:

*src/main/lit/example/HelloWorld.md:*

````markdown
# "Hello, world!" in Scala

This is a short example of literate programming in Scala.  This Markdown
file contains Scala snippets that, when collected, compile to a working
program.

The code in this file lives in the `example` package:

```scala
package example
```

The greeting will come from a source of static strings:

```scala
import example.data.Strings
```

The `HelloWorld` object extends the `App` trait, making it runnable by a
built-in `main` method:

```scala
object HelloWorld extends App {
  println(Strings.helloWorld)
}
```
````

*src/main/lit/example/data/Strings.md:*

````markdown
# Strings

This file contains static strings to be used within the application.
Note how we can mix Scala code in HelloWorld.md with Java code here.

This code lives in the `example.data` package:

```java
package example.data;
```

The `Strings` object provides access to the static strings:

```java
public class Strings {
  public static String helloWorld = "Hello, world";
}
```
````

Build and run it:

```
$ sbt
> compile
> run
Hello, world!
```

## Configuration

* `litSource` - source directory for literate files
    * Type: `File`
    * Default: `litSource := (sourceDirectory in Compile).value / "lit"`
      *(src/main/lit)*
* `litLangs` - languages to extract from literate files
    * Type: `Seq[File]`
    * Default: `litLangs := Seq("scala", "java")`
