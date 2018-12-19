package zawila.tic.tac.toe.core

import io.circe.{Decoder, Encoder, Json}

import scala.util.Try

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

object Field {
  implicit val FieldEncoder: Encoder[Field] =
    Encoder.instance { field: Field =>
      Json.fromString(field.value)
    }

  implicit val FieldDecoder: Decoder[Field] = Decoder.decodeString.map(s => s.toLowerCase match {
    case "x" => XField
    case "o" => OField
    case _ => EmptyField
  })

}
