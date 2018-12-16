package net.zawila.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra._

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

object BankHolidayComponent {

  val jsonUrl = "https://www.gov.uk/bank-holidays.json"
  val getBH = Request("GET", jsonUrl)

  final class Backend($: BackendScope[Unit, State]) {
    private def getBankHolidays(req: Request): Callback = {
      val ajax = Ajax(req.method, req.url)
        .send
        .onComplete { xhr =>
          def resp = Response(xhr.status, xhr.responseText)
          xhr.status match {
            case 200 => $.setState(State(Some((req, resp)), None))
            case _   => $.modState(_.copy(async = Some(Failed(req, Ajax.deriveErrorMessage(xhr)))))
          }

        }

      $.modState(
        _.copy(async = Some(InFlight(req))), // First, the state management
        ajax.asCallback)
    }

    def render(s: State): VdomElement = {
      def button(label: String, req: Request) =
        <.button(
          ^.marginLeft := "1em",
          ^.onClick --> getBankHolidays(req),
          ^.disabled := s.inFlight, // Don't allow more requests while one is already in progress
          label)

      <.div("HELLO Bank holiday",
          button("LABEl", getBH),
          <.div(s"${s.lastSuccess}"),
      <.div(s"${s.async}")
      )
    }

  }

  val Component = ScalaComponent.builder[Unit]("BankHolidayComponent")
    .initialState(State(None, None))
    .renderBackend[Backend]
    //.configure(Reusability.shouldComponentUpdate)
    .build
}