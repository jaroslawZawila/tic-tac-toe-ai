package zawila.tic.tac.toe.ai.server

import zawila.tic.tac.toe.ai.model.{Board, GameStatus, XField}

object Game {

  private var gameStatus =  GameStatus(Board(), false, None)

  def newGame = {
    gameStatus = GameStatus(Board(), false, None)
    gameStatus
  }

  def move(fieldNumber: Int) = {
    val newFields = gameStatus.board.fields.patch(fieldNumber, List(XField), 1)
    gameStatus = GameStatus(Board(newFields), false, None)
    gameStatus
  }

}
