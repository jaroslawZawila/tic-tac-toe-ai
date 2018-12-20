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

  val emptyRow = style(
    height(20 px)
  )

  val column = style(
    addClassName("col-4")
  )

  val square = style (
    addClassName(", btn btn-light border border-dark col-4"),
//    lineHeight(34 px),
    height(45 px),
    textAlign.center,
//    width(34 px),
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

