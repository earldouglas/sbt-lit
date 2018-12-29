[![Build Status](https://travis-ci.org/earldouglas/sbt-lit.svg?branch=master)](https://travis-ci.org/earldouglas/sbt-lit)

## Requirements

* sbt 1.0.0+

## Getting started

Add the sbt-lit plugin to your project:

*project/plugins.sbt:*

```scala
addSbtPlugin("com.earldouglas" % "sbt-lit" % "0.0.1")
```

Write some literate Scala code:

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

The code lives in the `example.data` package:

```scala
package example.data
```

The `Strings` object provides access to the static strings:

```scala
object Strings {
  val helloWorld = "Hello, world"
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
