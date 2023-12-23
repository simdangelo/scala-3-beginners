package com.rockthejvm.practice

import scala.annotation.tailrec

// singly linked list
abstract class LListGenerics[A] {
  def head: A
  def tail: LListGenerics[A]
  def isEmpty: Boolean

  def add(element: A): LListGenerics[A] = new ConsGenerics(element, this)
}

class EmptyGenerics[A] extends LListGenerics[A] {
  override def head: A = throw new NoSuchElementException
  override def tail: LListGenerics[A] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def toString = "[]"
}

class ConsGenerics[A](override val head: A, override val tail: LListGenerics[A]) extends LListGenerics[A] {
  override def isEmpty: Boolean = false
  override def toString: String = {
    @tailrec
    def concatenateElements(remainder: LListGenerics[A], acc: String): String =
      if (remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

    s"[${concatenateElements(this.tail, s"$head")}]"
  }
}

object LListTestGenerics {
  def main(args: Array[String]): Unit = {
    val empty = new EmptyGenerics[Int]
    println(empty)
    println(empty.isEmpty)

    val first3numbers = new ConsGenerics(1, new ConsGenerics(2, new ConsGenerics(3, empty)))
    println(first3numbers)

    val first3numbers_v2 = empty.add(1).add(2).add(3)
    println(first3numbers_v2)
    println(first3numbers_v2.isEmpty)

    val someStrings = new ConsGenerics("dog", new ConsGenerics("cat", new EmptyGenerics))
    println(someStrings)

  }
}
