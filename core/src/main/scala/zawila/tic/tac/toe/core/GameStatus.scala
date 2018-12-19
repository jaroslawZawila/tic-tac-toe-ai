package zawila.tic.tac.toe.core

import scala.scalajs.js.annotation.JSExport

final case class GameStatus(board: Board, finished: Boolean, winner: Option[Field], nextPlayer: Field)

object GameStatus {
  @JSExport
  def getEmpty = GameStatus(Board(), false, None, XField)

}