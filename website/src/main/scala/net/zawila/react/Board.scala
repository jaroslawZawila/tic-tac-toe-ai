package net.zawila.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra._
import scalacss.ScalaCssReact._

object Board  {

  case class State(squares: List[String], isXNext: Boolean)

  val winningComibinations = Set(
    Seq( 0, 1, 2),
    Seq( 3, 4, 5),
    Seq( 6, 7, 8),
    Seq( 0, 3, 6),
    Seq( 1, 4, 7),
    Seq( 2, 5, 8),
    Seq( 0, 4, 8),
    Seq( 2, 4, 6)
  )

  def calculateWinner(s: List[String]) = {
    winningComibinations.foldLeft(Option.empty[String]) { (x, y) =>
      y match {
        case a :: b :: c :: Nil => {
          if (s(a) == s(b) && s(a) == s(c)) Some(s(a)) else x
        }
        case _ => x
      }
    }
  }

  final class Backend($: BackendScope[Unit, State]) {

    def handleClick(int: Int) = {
      $.modState(s => {
        val xOrO = if(s.isXNext) "X" else "O"
        val square = s.squares(int)
        if(square != "X" && square != "O") {
          State(s.squares.updated(int, xOrO), !s.isXNext)
        } else
        s
      })
    }

    def render(p: Unit, state: State): VdomElement = {
      val xOrO = if(state.isXNext) "X" else "O"
      val winner = calculateWinner(state.squares)
      val status = winner.map(w => s"Winner: $w").getOrElse(s"Next player: '$xOrO'")
      <.div(
        <.div(^.className := "status", status, CSS.body),
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


    def renderSquare(i: Int, state: State) = Square(state.squares(i), handleClick(i))
  }

  val Component = ScalaComponent.builder[Unit]("Board")
    .initialState(State((11 to 19).map(_.toString).toList, true))
    .renderBackend[Backend]
    //.configure(Reusability.shouldComponentUpdate)
    .build
}
