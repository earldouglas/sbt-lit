# Hello, world!

This is a short example of literate programming in Scala.  This Markdown
file contains Scala snippets that, when collected, compile to a working
program.

The code in this file lives in the `example` package:

```scala
package example
```

A couple of dependencies are needed:

```scala
import java.io.File
import java.io.PrintWriter
import example.data.Strings
```

The `HelloWorld` object extends the `App` trait, making it runnable by a
built-in `main` method:

```scala
object HelloWorld extends App {
  val pw = new PrintWriter(new File("./target/hello-world.txt"))
  pw.write(Strings.helloWorld)
  pw.close
}
```
