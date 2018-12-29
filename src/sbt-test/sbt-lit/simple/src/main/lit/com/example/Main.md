# Test

```scala
package com.example
```

```scala
import java.io.File
import java.io.PrintWriter
```

```scala
object Main extends App {
  val pw = new PrintWriter(new File("./target/hello-world.txt"))
  pw.write("Hello, world")
  pw.close
}
```
