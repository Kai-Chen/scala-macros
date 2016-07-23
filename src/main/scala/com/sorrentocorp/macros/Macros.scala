package com.sorrentocorp.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context

object CheckWhenCompile {
  def sequentially[A, B](body: A => B): A => B = macro seqImpl[A, B]
  def seqImpl[A: c.WeakTypeTag, B: c.WeakTypeTag](c: Context)(body: c.Expr[A => B]): c.Expr[A => B] = {
    import c.universe._
    c.info(c.enclosingPosition, "Checking body " + body.tree, true)

    body.tree match {
      case Function(_, Block(stats, expr)) =>
        c.info(c.enclosingPosition, "matched function", false)
        c.info(c.enclosingPosition, "stats " + stats, true)
        c.info(c.enclosingPosition, "expr " + expr, true)
      case _ =>
        c.warning(c.enclosingPosition, "not matched")
        c.warning(c.enclosingPosition, body.tree.toString)
    }
    body
  }
}
