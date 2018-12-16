package net.zawila.react

import scalacss.DevDefaults._
import scalacss.internal.mutable.StyleSheet

object CSS extends StyleSheet.Inline  {
  import dsl._

  val body = style("body")(
    fontSize(14 px),
    margin(20 px)
  )

  val square = style("Square") (
    backgroundColor(Color("#fff")),
    border(1 px, solid, Color("999")),
    fontWeight.bold,
    lineHeight(34 px),
    height(34 px),
    marginRight(-1 px),
    marginTop(-1 px),
    padding(0 px),
    textAlign.center,
    width(34 px),

    &.focus(
      outline.none,
      backgroundColor(Color("#ddd"))

    )
  )

  val boardRow = style("board-row")(
    &.after(
      clear.both,
      display.table
    )

  )

  val status = style("status")(
    marginBottom(10 px)
  )

  val game = style("game")(
    display.flex,
    flexDirection.row
  )

  }

