import sbt.Keys._
import sbt._

object Share {
  lazy val settings = Seq(
    resolvers += Resolver.sonatypeRepo("snapshots"),
    scalacOptions ++= Seq("-Ypartial-unification")
  )

}
