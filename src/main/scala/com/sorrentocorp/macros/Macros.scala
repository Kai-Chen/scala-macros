package com.sorrentocorp.macros

import scala.concurrent.Future
import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context

object CheckWhenCompile {
  def sequentially[A, B](body: A => B): A => B = macro seqImpl[A, B]
  def seqImpl[A: c.WeakTypeTag, B: c.WeakTypeTag](c: Context)(body: c.Expr[A => B]): c.Expr[A => B] = {
    import c.universe._

    def check(tree: Tree): Unit =
      if (tree.tpe <:< typeOf[Future[_]])
        c.error(c.enclosingPosition, s"line contains asynchronous code; it shouldn't be in a transactional block")

    body.tree match {
      case Function(_, Block(stats, expr)) =>
        stats foreach check
        check(expr)

      case x@_ =>
        c.error(c.enclosingPosition, s"Expected an anomynous function, but found $x")
    }

    body
  }
}
