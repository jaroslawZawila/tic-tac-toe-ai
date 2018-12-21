package zawila.tic.tac.toe.ai.server

import zawila.tic.tac.toe.core._

import scala.util.Random

object Game {

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

  def calculateWinner(s: List[Field]) = {
    winningComibinations.foldLeft(Option.empty[Field]) { (x, y) =>
      y match {
        case a :: b :: c :: Nil => {
          if (s(a) != EmptyField && s(a) == s(b) && s(a) == s(c)) Some(s(a)) else x
        }
        case _ => x
      }
    }
  }

  private var gameStatus =  GameStatus(Board(), false, None, XField)

  def newGame = {
    gameStatus = GameStatus(Board(), false, None, XField)
    gameStatus
  }


  def randomeGame(number: Int) = {
    makeMove(number)
    if(!gameStatus.finished) {
      makeMove(computerMove)
    }
  }

  private def makeMove(fieldNumber: Int) = {
    if(gameStatus.finished == false && gameStatus.board.fields(fieldNumber) == EmptyField) {
      val newFields = gameStatus.board.fields.patch(fieldNumber, List(gameStatus.nextPlayer), 1)
      val next = if (gameStatus.nextPlayer == XField) OField else XField
      val winner = calculateWinner(newFields)
      println(winner)
      gameStatus = GameStatus(Board(newFields), winner.isDefined, winner, next)
      gameStatus
    }else {
      gameStatus
    }

  }

  def computerMove: Int = {
      val random = Random.nextInt(9)

      gameStatus.board.fields(random) match {
        case EmptyField => random
        case _ => computerMove
      }
  }

}
