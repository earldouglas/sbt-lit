package com.earldouglas.sbtlit

import sbt.Keys._
import sbt._
import sbt.internal.io.Source

object SbtLit extends AutoPlugin {

  override def requires = plugins.JvmPlugin
  override def trigger = allRequirements

  object autoImport {
    lazy val litSource = taskKey[File]("source directory for literate files")
  }

  import autoImport._

  override val projectSettings: Seq[Def.Setting[_]] =
    Seq( watchSources += new Source(litSource.value, AllPassFilter, NothingFilter)
       , litSource := (sourceDirectory in Compile).value / "lit"
       ) ++ inConfig(Compile) {
      sourceGenerators +=
        Def.task({

          val sourceFiles: Seq[File] =
            (litSource.value ** "*.md").get

          val inputsCache: File =
            streams.value.cacheDirectory / "lit-inputs"

          val destFiles: Seq[File] =
            sourceFiles.flatMap({ sourceFile =>
              IO.relativize( litSource.value
                           , sourceFile
                           ) map { sourceRelative =>

                val destRelative: File =
                   new File(sourceRelative.replaceAll("\\.md$", ".scala"))

                val destFile: File =
                  IO.resolve( sourceManaged.value
                            , destRelative
                            )

                val tracker =
                  Tracked.inputChanged(inputsCache / sourceRelative)({ (inChanged, _: HashFileInfo) =>
                    if (inChanged || !destFile.exists) {
                      val input: String = IO.read(sourceFile)
                      val output: String = Extract(input, "scala")
                      IO.write(destFile, output)
                    }
                  })

                tracker(FileInfo.hash(sourceFile))

                destFile
              }
            })
            
          destFiles
        }).taskValue
    }
}
