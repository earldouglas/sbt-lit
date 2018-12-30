package com.earldouglas.sbtlit

import sbt.Keys._
import sbt._
import sbt.internal.io.Source

object SbtLit extends AutoPlugin {

  override def requires = plugins.JvmPlugin
  override def trigger = allRequirements

  object autoImport {
    lazy val litSource = taskKey[File]("source directory for literate files")
    lazy val litLangs = taskKey[Seq[String]]("languages to extract from literate files")
  }

  import autoImport._

  override val projectSettings: Seq[Def.Setting[_]] =
    Seq( watchSources += new Source(litSource.value, AllPassFilter, NothingFilter)
       , litSource := (sourceDirectory in Compile).value / "lit"
       , litLangs := Seq("scala", "java")
       ) ++ inConfig(Compile) {
      sourceGenerators +=
        Def.task({

          val sourceDir: File =
            litSource.value

          val sourceFiles: Seq[File] =
            (sourceDir ** "*.md").get

          val sourceLangs: Seq[String] =
            litLangs.value

          val inputsCache: File =
            streams.value.cacheDirectory / "lit-inputs"

          val destDir: File =
            sourceManaged.value

          val destFiles: Seq[File] =
            sourceFiles.flatMap({ sourceFile =>
              IO.relativize( sourceDir
                           , sourceFile
                           ).toSeq flatMap { sourceRelative =>

                val tracker =
                  Tracked.inputChanged(inputsCache / sourceRelative)({ (inChanged, _: HashFileInfo) =>
                    sourceLangs map { lang =>

                      val destRelative: File =
                         new File(sourceRelative.replaceAll("\\.md$", s".${lang}"))

                      val destFile: File =
                        IO.resolve( destDir
                                  , destRelative
                                  )

                      if (inChanged || !destFile.exists) {
                        val input: String = IO.read(sourceFile)
                        val output: String = Extract(input, lang)
                        IO.write(destFile, output)
                      }

                      destFile
                    }
                  })

                tracker(FileInfo.hash(sourceFile))
              }
            })
            
          destFiles
        }).taskValue
    }
}
