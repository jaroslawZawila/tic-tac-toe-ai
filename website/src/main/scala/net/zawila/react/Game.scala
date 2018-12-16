package net.zawila.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.document
import scalacss.DevDefaults._

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel, JSImport}  // Always use dev settings

object Game {

  final class Backend($: BackendScope[Unit, Unit]) {
    def render(p: Unit): VdomElement =
      <.div(^.className := "game",
        <.div(^.className := "game-board",
          Board.Component()
        ),
        <.div(^.className := "game-info",
          <.div,
          <.ol
        ),
        <.div(
        BankHolidayComponent.Component()
      )
      )
  }

  val Component = ScalaComponent.builder[Unit]("Game")
    .renderBackend[Backend]
    //.configure(Reusability.shouldComponentUpdate)
    .build

  @JSImport("bootstrap", JSImport.Namespace)
  @JSExport
  @JSExportTopLevel("net.zawila.react.Game.main")
//  def main(): Unit = {
  def main(args: Array[String]): Unit = {
    CSS.addToDocument()
    Game.Component().renderIntoDOM(document.body)
  }
}