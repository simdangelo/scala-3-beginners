package com.rockthejvm.part2oop

object Generics {

  abstract class MyList[A] {
    def head: A
    def tail: MyList[A]
  }

  class Empty[A] extends MyList[A] { // "generic" list
    override def head: A = throw new NoSuchElementException
    override def tail: MyList[A] = throw new NoSuchElementException
  }

  class NonEmpty[A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  val listOfIntegers: MyList[Int] = new NonEmpty[Int](1, new NonEmpty[Int](2, new Empty[Int]))

  val firstNumber: Int = listOfIntegers.head
  val adding = firstNumber + 3


  // multiple generic types
  trait MyMap[K, V]

  object MyList {
    def from2Elements[A](elem1: A, elem2: A): MyList[A] =
      new NonEmpty[A](elem1, new NonEmpty[A](elem2, new Empty[A]))
  }

  // calling methods
  val first2Numbers = MyList.from2Elements[Int](1,2)
  val first2Numbers_v2 = MyList.from2Elements(1, 2)
  val first2Numbers_v3 = new NonEmpty(1, new NonEmpty(2, new Empty))

  /**
   * EXERCISE:
   *  Add a type argument that make generalization of the Linked List that we implemented in LList file in "practice" folder
   */

  def main(args: Array[String]): Unit = {
    println(firstNumber)
  }
}
