
name := "tic-tac-toe-ai"

version := "0.1"

scalaVersion := "2.12.8"

lazy val web = project
  .in(file("web-server"))
  .settings(Dependencies.webServer)
  .settings(Share.settings)


lazy val website = project
  .in(file("website"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, WebScalaJSBundlerPlugin)
  .settings(Share.settings)
  .settings(FrontWeb.settings)
  .settings(
    scalaJSUseMainModuleInitializer := true,
//    scalaJSMainModuleInitializer := Some(mainMethod("net.zawila.react.Game", "main")),
    npmDependencies in Compile ++= FrontWeb.npm,
//    npmAssets ++= NpmAssets.ofProject(LocalProject("tic-tac-toe-ai/website")) { nodeModules =>
//      (nodeModules / "bootstrap").allPaths // sbt 1.0.0+
//    }.value,
    mainClass in Compile := Some("net.zawila.react.Game")
  )
