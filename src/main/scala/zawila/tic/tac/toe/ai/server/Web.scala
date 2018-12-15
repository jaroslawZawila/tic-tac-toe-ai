package zawila.tic.tac.toe.ai.server

import cats.effect._
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe.CirceEntityEncoder._
import org.http4s.dsl.io._


object  Web {

  val helloWorldService = HttpService[IO] {
    case GET -> Root / "game" / "start" =>
      Ok(Game.newGame.asJson)

    case GET -> Root / "game" / "move" / IntVar(block) =>
      Ok(Game.move(block).asJson)

  }

}
