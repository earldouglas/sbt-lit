package com.earldouglas.sbtlit

import java.io.FileInputStream
import sbt.Keys._
import sbt._
import scala.io.Source

object SbtLit extends sbt.AutoPlugin {

  override def requires = plugins.JvmPlugin
  override def trigger = allRequirements

  override val projectSettings: Seq[Def.Setting[_]] =
    inConfig(Compile) {
      sourceGenerators +=
        Def.task({

          val sourceDir: File =
            (sourceDirectory in Compile).value / "lit"

          val sourceFiles: Seq[File] =
            (sourceDir ** "*.md").get

          val destFiles: Seq[File] =
            sourceFiles.flatMap({ sourceFile =>

              val input: String =
                Source.fromInputStream(new FileInputStream(sourceFile)).mkString

              IO.relativize( sourceDir
                           , sourceFile
                           ) map { sourceRelative =>

                val destRelative: File =
                   new File(sourceRelative.replaceAll("\\.md$", ".scala"))

                val destFile: File =
                  IO.resolve( sourceManaged.value
                            , destRelative
                            )

                  val output: String = Extract(input, "scala")
                  IO.write(destFile, output)

                  destFile
                }
            })
            
          destFiles
        }).taskValue
    }
}
