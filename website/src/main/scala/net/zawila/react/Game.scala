package net.zawila.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.Ajax
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.document
import scalacss.DevDefaults._

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel, JSImport}
import io.circe.parser.decode
import io.circe.generic.auto._
import net.zawila.react.Board.BoardProperty
import zawila.tic.tac.toe.core.GameStatus
//

case class Request(method: String, url: String)
case class Response(status: Int, response: String)

sealed trait AsyncState
case class InFlight(req: Request) extends AsyncState
case class Failed(req: Request, reason: String) extends AsyncState


case class State(lastSuccess: Option[(Request, Response)],
                 async      : Option[AsyncState]) {

  // Is a request currently in-flight?
  val inFlight: Boolean =
    async.exists {
      case _: InFlight => true
      case _: Failed   => false
    }
}

object Game {

  final class Backend($: BackendScope[Unit, GameStatus]) {

    val startGameRequest = Request("GET", "http://localhost:8080/game/start")
    def pressRequest(i: Int) = Request("GET", s"http://localhost:8080/game/move/$i")

    private def startGame(req: Request): Callback = {
      val ajax = Ajax(req.method, req.url)
        .send
        .onComplete { xhr =>
          def resp = Response(xhr.status, xhr.responseText)
          $.setState(decode[GameStatus](xhr.responseText).right.get)
        }

      $.modState(_ => GameStatus.getEmpty, ajax.asCallback)
    }

    private def press: Int => Callback = int => {
      startGame(pressRequest(int))
    }

    def render(p: Unit, s: GameStatus): VdomElement =
      <.div(^.className := "game",
        <.div(
          <.button (^.onClick --> startGame(startGameRequest), "Start the game"),
        ),
        <.div(^.className := "game-board",
          Board.Component(BoardProperty(press, s.board.fields))
        ),
        <.div(^.className := "game-info",
          <.div,
          <.ol
        ),
        <.div(s.toString)
      )
  }

  val Component = ScalaComponent.builder[Unit]("Game")
    .initialState(GameStatus.getEmpty)
    .renderBackend[Backend]
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