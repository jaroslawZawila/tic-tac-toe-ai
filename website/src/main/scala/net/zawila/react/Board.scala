package net.zawila.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra._
import scalacss.ScalaCssReact._
import zawila.tic.tac.toe.core._

object Board  {

  case class BoardProperty(callback: Int => Callback, fields: List[Field])

  final class Backend($: BackendScope[BoardProperty, Unit]) {

    def render(state: BoardProperty): VdomElement = {
      <.div(
        <.div(^.className := "status", "", CSS.body),
        <.div(^.className := "board-row",
          renderSquare(0, state),
          renderSquare(1, state),
          renderSquare(2, state)),
        <.div(^.className := "board-row",
          renderSquare(3, state),
          renderSquare(4, state),
          renderSquare(5, state)),
        <.div(^.className := "board-row",
          renderSquare(6, state),
          renderSquare(7, state),
          renderSquare(8, state))
      )
    }

    def renderSquare(i: Int, state: BoardProperty) = Square(state.fields(i).value, state.callback(i))
  }

  val Component = ScalaComponent.builder[BoardProperty]("Board")
    .renderBackend[Backend]
    .build
}
