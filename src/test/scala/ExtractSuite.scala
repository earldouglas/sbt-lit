import com.earldouglas.sbtlit.Extract
import org.scalatest.FunSuite
import scala.io.Source

class ExtractSuite extends FunSuite {

  val source: String =
    Source.fromInputStream(getClass.getResourceAsStream("/example.md")).mkString

  test("extract Scala code") {
    val expected: String =
      """|val x = 42
         |println(x)
         |""".stripMargin
    assert(Extract(source, "scala") === expected)
  }

  test("extract JavaScript code") {
    val expected: String =
      """|var x = 42;
         |console.log(x);
         |""".stripMargin
    assert(Extract(source, "javascript") === expected)
  }

  test("extract Haskell code") {
    val expected: String =
      """|x :: Int
         |x = 42
         |main :: IO ()
         |main = putStrLn $ show x
         |""".stripMargin
    assert(Extract(source, "haskell") === expected)
  }
}
