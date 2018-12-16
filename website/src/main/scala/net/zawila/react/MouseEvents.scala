package net.zawila.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.MouseEvent
import org.scalajs.dom
import japgolly.scalajs.react.extra._
object MouseEvents  {

  final class Backend($: BackendScope[Unit, String]) extends OnUnmount {
    def logEvent(desc: String)       = $.modState(_ + "\n" + desc)
    def logMouseEnter(e: MouseEvent) = logEvent(s"Mouse enter @ ${e.pageX},${e.pageY}")
    val logWindowClick               = logEvent("Window clicked.")
    val logLocalClick                = logEvent("Component clicked.")

    def render(state: String) =
      <.pre(
        ^.border  := "solid 1px black",
        ^.width   := "90ex",
        ^.height  := "20em",
        ^.padding := "2px 6px",
        state)
  }

  val Component = ScalaComponent.builder[Unit]("MouseEvents ")
    .initialState("Local mouseenter events + local/global click events will appear here.")
    .renderBackend[Backend]

    // Listen to mouseenter events within the component
    .configure(EventListener[MouseEvent].install("mouseenter", _.backend.logMouseEnter))

    // Listen to click events
    .configure(EventListener.install("click", _.backend.logLocalClick))
    .configure(EventListener.install("click", _.backend.logWindowClick, _ => dom.window))

    .build
}
