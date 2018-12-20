package net.zawila.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import zawila.tic.tac.toe.core.Field
import scalacss.ScalaCssReact._

object Square {

  case class Props(number: String, onClick: Callback)

  val Component = ScalaFnComponent[Props](
      p => <.button(
        ^.onClick --> p.onClick,
        CSS.square,
      p.number))

  def apply(f:String, c: Callback) = Component(Props(f, c))
}