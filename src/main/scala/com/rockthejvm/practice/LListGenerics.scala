package com.rockthejvm.practice

import scala.annotation.tailrec

// singly linked list
abstract class LListGenerics[A] {
  def head: A
  def tail: LListGenerics[A]
  def isEmpty: Boolean
  def add(element: A): LListGenerics[A] = new ConsGenerics(element, this)

  // concatenation function
  infix def ++(anotherList: LListGenerics[A]): LListGenerics[A]

  def map[B](transformer: Transformer[A, B]): LListGenerics[B]
  def filter(predicate: Predicate[A]): LListGenerics[A]
  def flatMap[B](transformer: Transformer[A, LListGenerics[B]]): LListGenerics[B]
}

case class EmptyGenerics[A]() extends LListGenerics[A] {
  override def head: A = throw new NoSuchElementException
  override def tail: LListGenerics[A] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def toString = "[]"

  override def map[B](transformer: Transformer[A, B]): LListGenerics[B] = EmptyGenerics()
  override def filter(predicate: Predicate[A]): LListGenerics[A] = this
  override infix def ++(anotherList: LListGenerics[A]): LListGenerics[A] = anotherList
  override def flatMap[B](transformer: Transformer[A, LListGenerics[B]]): LListGenerics[B] = EmptyGenerics()

}

case class ConsGenerics[A](override val head: A, override val tail: LListGenerics[A]) extends LListGenerics[A] {
  override def isEmpty: Boolean = false
  override def toString: String = {
    @tailrec
    def concatenateElements(remainder: LListGenerics[A], acc: String): String =
      if (remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

    s"[${concatenateElements(this.tail, s"$head")}]"
  }

  override def map[B](transformer: Transformer[A, B]): LListGenerics[B] = {
    ConsGenerics(transformer.transform(head), tail.map(transformer))
  }
  /*
  breaking down this map function with this example [1,2,3].map(n*2)
  [1,2,3].map(n*2) =
  new ConsGenerics(2, [2,3].map(n*2)) =
  new ConsGenerics(2, new Cons(4, [3].map(n*2))) =
  new ConsGenerics(2, new Cons(4, new Cons(6, [].map(n*2)))) =
  new ConsGenerics(2, new Cons(4, new Cons(6, [])))
   */

  override def filter(predicate: Predicate[A]): LListGenerics[A] =
    if (predicate.test(head)) ConsGenerics(head, tail.filter(predicate))
    else tail.filter(predicate)

  override infix def ++(anotherList: LListGenerics[A]): LListGenerics[A] =
    ConsGenerics(head, tail ++ anotherList)

  override def flatMap[B](transformer: Transformer[A, LListGenerics[B]]): LListGenerics[B] =
    transformer.transform(head) ++ tail.flatMap(transformer)

}

/**
 * "ANONYMOUS CLASSES" LESSON
 * Exercise: LList extension
 *
 * 1. Generic trait Predicate[T] with a little method test(T)⇒Boolean
 * 2. Generic trait Transformer[A, B] with a method transform(A)⇒B
 * 3. LList:
 * - map(transformer) ⇒ LList
 * - filter(predicate) ⇒ LList
 * - flatMap(transformer from A to LList[B]) ⇒ LList[B]
 *
 * class EvenPredicate extends Predicate[Int
 * class StringToIntTransformer extends Transformer[String, Int]
 *
 * [1,2,3].map(n*2) = [2,4,6]
 * [1,2,3,4].filter(n%2) = [2,4]
 * [1,2,3].flatMap(n ⇒ [n, n+1]) ⇒ [1,2,2,3,3,4]
 */

// EXERCISE1: Predicate[T]
trait Predicate[T] {
  def test(element: T): Boolean
}

// Example of how Predicate[T] would look like ("filter" function):
class EvenPredicate extends Predicate[Int] {
  override def test(element: Int) =
    element % 2 == 0
}

// EXERCISE2: Transformer[A, B]
trait Transformer[A, B] {
  def transform(value: A): B
}

// Example of how Transformer[A, B] would look like ("map" function)::
class Doubler extends Transformer[Int, Int]:
  override def transform(value: Int): Int =
    value * 2

// "flatMap" function
class DoublerList extends Transformer[Int, LListGenerics[Int]] {
  override def transform(value: Int): LListGenerics[Int] = {
    ConsGenerics(value, ConsGenerics(value + 1, EmptyGenerics()))
  }
}


object LListTestGenerics {
  def main(args: Array[String]): Unit = {
    val empty = EmptyGenerics[Int]()
    println(empty)
    println(empty.isEmpty)

    val first3numbers = ConsGenerics(1, ConsGenerics(2, ConsGenerics(3, empty)))
    println(first3numbers)

    val first3numbers_v2 = empty.add(1).add(2).add(3)
    println(first3numbers_v2)
    println(first3numbers_v2.isEmpty)

    val someStrings = ConsGenerics("dog", ConsGenerics("cat", EmptyGenerics()))
    println(someStrings)


    // EXERCISE1 with Anonymous class
    val evenPredicate = new Predicate[Int] {
      override def test(element: Int) =
        element % 2 == 0
    }

    // EXERCISE2 with Anonymous class
    val doubler = new Transformer[Int, Int] {
      override def transform(value: Int) =
        value * 2
    }

    // map TESTING
    //    apply map method to first3Numbers with Doubler transformer
    val numbersDoubled = first3numbers.map(new Doubler) // or first3numbers.map(doubler) since we just instantiate a "doubler" transform as anonymous function
    println(numbersDoubled)

    //    Now, let’s apply map method to first3Numbers with DoublerList transformer:
    val numbersNested = first3numbers.map(new DoublerList)
    println(numbersNested)

    // filter TESTING
    val onlyEvenNumbers = first3numbers.filter(new EvenPredicate) // or first3numbers.map(evenPredicate) since we just instantiate an evenPredicate transform as anonymous function
    println(onlyEvenNumbers)

    // concatenation (++) TESTING
    val listInBothWays = first3numbers ++ first3numbers_v2
    println(s"Concatenation Test: $listInBothWays")

    // flatMap TESTING
    val flattenedList = first3numbers.flatMap(new DoublerList)
    println(s"Flattened list Test: $flattenedList")
  }
}
