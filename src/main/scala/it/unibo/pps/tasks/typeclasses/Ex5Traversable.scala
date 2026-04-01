package it.unibo.pps.tasks.typeclasses

import it.unibo.pps.u03.extensionmethods.Sequences.Sequence, Sequence.*
import it.unibo.pps.u03.extensionmethods.Optionals.Optional, Optional.*

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A] 
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  def log[A](a: A): Unit = println("The next element is: "+a)

  def logAll[T[_]: Traversable, A](n: T[A]): Unit =
    val traversable = summon[Traversable[T]]
    traversable.consumer(n)(log)

  trait Traversable[T[_]]:
    def consumer[A](t: T[A])(f: A => Unit): Unit

  given Traversable[Optional] with
    def consumer[A](t: Optional[A])(f: A => Unit): Unit = t match {
      case Just(a) => f(a)
      case _ => ()
    }

  given Traversable[Sequence] with
    def consumer[A](t: Sequence[A])(f: A => Unit): Unit = t match {
      case Cons(head, tail) => f(head); consumer(tail)(f)
      case _ => ()
    }

    @main def tryTraversable =
      val si = Cons(10, Cons(20, Cons(30, Nil())))
      logAll(si)
      
      logAll(Just(15))
      logAll(None())

