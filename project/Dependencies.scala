import sbt.Keys.{libraryDependencies, _}
import sbt._
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._

object Dependencies {

  private val http4sVersion = "0.18.21"

  private lazy val webServiceDeps = Seq(
    "org.http4s" %% "http4s-dsl" % http4sVersion,
    "org.http4s" %% "http4s-blaze-server" % http4sVersion,
    "org.http4s" %% "http4s-blaze-client" % http4sVersion,
    "org.http4s" %% "http4s-circe" % http4sVersion,
    "io.circe" %% "circe-generic" % "0.9.3"
  )

  lazy val webServer = Seq(
    libraryDependencies ++= webServiceDeps
  )

  lazy val core = Seq(
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-circe" % http4sVersion,
      "com.github.japgolly.scalajs-react" %%% "core" % "1.3.1"
//      "io.circe" %% "circe-core"    % "0.10.0",
//      "io.circe" %% "circe-generic" % "0.10.0",
//      "io.circe" %% "circe-parser"  % "0.10.0"
    )
  )
}
