package zawila.tic.tac.toe.ai.model

import io.circe.{Encoder, Json}


sealed trait Field {
  val value: String
}
final case object XField extends Field {
  override val value: String = "X"}
final case object OField extends Field {
  override val value: String = "O"
}
final case object EmptyField extends Field {
  override val value: String = ""
}

final case class GameStatus(board: Board, finished: Boolean, winner: Option[String])

final case class Board private(fields: List[Field])

object Board {
  def apply(fields: List[Field]) = {
    new Board(fields)
  }

  def apply() = new Board(List.fill(9)(EmptyField))

}

object Field {
  implicit val HelloEncoder: Encoder[Field] =
    Encoder.instance { field: Field =>
      Json.fromString(field.value)
    }
}