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

<pre>
# "Hello, world!" in Scala

First, we choose a package name:

```scala
package example
```

Next, we write a `main` method by extending the `App` trait:

```scala
object Main extends App {
  println("Hello, world!")
}
```
</pre>

Build and run it:

```
$ sbt
> compile
> run
Hello, world!
```
