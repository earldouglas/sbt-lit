import com.earldouglas.sbtlit.Extract
import org.scalatest.FunSuite
import scala.io.Source

class ExtractSuite extends FunSuite {

  def read(filename: String): String =
    Source.fromInputStream(getClass.getResourceAsStream(filename)).mkString

  test("extract Scala code") {

    val source: String =
      read("/lit/example/HelloWorld.md")

    val expected: String =
      """|package example
         |import java.io.File
         |import java.io.PrintWriter
         |import example.data.Strings
         |object HelloWorld extends App {
         |  val pw = new PrintWriter(new File("./target/hello-world.txt"))
         |  pw.write(Strings.helloWorld)
         |  pw.close
         |}
         |""".stripMargin

    assert(Extract(source, "scala") === expected)
  }

  test("extract Java code") {

    val source: String =
      read("/lit/example/data/Strings.md")

    val expected: String =
      """|package example.data;
         |public class Strings {
         |  public static String helloWorld = "Hello, world";
         |}
         |""".stripMargin

    assert(Extract(source, "java") === expected)
  }
}
