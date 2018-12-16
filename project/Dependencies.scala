import sbt.Keys.{libraryDependencies, _}
import sbt._


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

}
