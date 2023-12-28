package com.rockthejvm.part3fp

object WhatsAFunction {

  trait MyFunction[A, B] {
    def apply(arg: A): B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(arg: Int) = arg * 2
  }

  val meaningOfLife = 42
  val meaningDoubled = doubler(meaningOfLife) //doubler.apply(meaningOfLife)

  // function types
  val doublerStandard = new Function1[Int, Int] {
    override def apply(arg: Int) = arg * 2
  }
  val meaningDoubled_v2 = doublerStandard(meaningOfLife)

  val adder = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int =
      a + b
  }
  val anAddition = adder(2, 6)

  val aThreeArgFunctions = new Function4[Int, String, Double, Boolean, Int] {
    override def apply(v1: Int, v2: String, v3: Double, v4: Boolean): Int = ???
  }

  /**
   * Exercises.
   *
   * 1. Define a function which takes 2 strings and concatenates them
   * 2. Code change in `LListGenerics` in order to remove `Predicate` and `Transformer` traits and replace them with the appropriate function types.
   * 3. Define a function which takes an Int as argument and returns another function as result.
   *
   */

  // EXERCISE1
  val concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String) = a + b
  }

  // EXERCISE2 is a refactoring ofLListGenerics.scala

  // EXERCISE3
  val superAdder = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int) = new Function1[Int, Int] {
      override def apply(y: Int) = x + y
    }
  }

  val adder2 = superAdder(2)
  val anAddition_v2 = adder2(67) // 69
  // currying
  val anAddition_v3 = superAdder(2)(67)


  def main(args: Array[String]): Unit = {
    println(concatenator("I love ", "Scala"))
  }

}
