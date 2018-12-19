package zawila.tic.tac.toe.core

final case class Board private(fields: List[Field])

object Board {
  def apply(fields: List[Field]) = {
    new Board(fields)
  }

  def apply() = new Board(List.fill(9)(EmptyField))

}