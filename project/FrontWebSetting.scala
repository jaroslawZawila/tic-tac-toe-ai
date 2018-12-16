import sbt.Keys.{libraryDependencies, _}
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._

object FrontWeb {

  lazy val npm = Seq(
    "react" -> "16.5.1",
    "react-dom" -> "16.5.1",
    "bootstrap" -> "4.1.3")

  lazy val settings = Seq (
      libraryDependencies ++= Seq(
      "com.github.japgolly.scalajs-react" %%% "core" % "1.3.1",
      "com.github.japgolly.scalajs-react" %%% "extra" % "1.3.1",
      //  "com.github.japgolly.scalacss" %% "core" % "0.5.5",
      "com.github.japgolly.scalacss" %%% "ext-react" % "0.5.5"
    )
  )
}
