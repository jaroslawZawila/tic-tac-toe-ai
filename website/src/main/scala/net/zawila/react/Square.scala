package net.zawila.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object Square {

  case class Props(number: String, onClick: Callback)

  val Component = ScalaFnComponent[Props](
      p => <.button(
      ^.className := "btn btn-success",
      ^.onClick --> p.onClick,
      p.number))

  def apply(s:String, c: Callback) = Component(Props(s.toString, c))
}