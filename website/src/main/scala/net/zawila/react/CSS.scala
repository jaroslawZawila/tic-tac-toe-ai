package net.zawila.react

import scalacss.DevDefaults._
import scalacss.internal.mutable.StyleSheet

object CSS extends StyleSheet.Inline  {
  import dsl._

  val body = style(
    addClassName("container-fluid")
  )

  val row = style(
    addClassName("row")
  )

  val column = style(
    addClassName("col")
  )

  val square = style("Square") (
    addClassName("btn, btn-primary"),
    lineHeight(34 px),
    height(34 px),
    textAlign.center,
    width(34 px),

    &.focus(
      outline.none,
      addClassName("btn, btn-success")
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

