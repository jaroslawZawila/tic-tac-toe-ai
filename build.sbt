name := "tic-tac-toe-ai"

version := "0.1"

scalaVersion := "2.12.8"

lazy val web = project
  .in(file("web-server"))
  .settings(Dependencies.webServer)
  .settings(Share.settings)

