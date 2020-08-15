package com.earldouglas.sbtlit

import com.vladsch.flexmark.ast.FencedCodeBlock
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.data.MutableDataSet
import scala.collection.JavaConverters._

object Extract {

  def apply(source: String, lang: String): String = {
    val options = new MutableDataSet();
    val parser = Parser.builder(options).build()
    val document = parser.parse(source)
    extract(document, lang).mkString
  }

  private def extract(n: Node, lang: String): List[String] = {
    var lines: List[String] = Nil
    if (n.isInstanceOf[FencedCodeBlock]) {
      val fcb = n.asInstanceOf[FencedCodeBlock]
      if (fcb.getInfo.toString == lang) {
        lines ++= fcb.getContentLines.asScala.map(_.toString)
      }
    }
    n.getChildren.asScala foreach { c =>
      lines ++= extract(c, lang)
    }
    lines
  }

}
