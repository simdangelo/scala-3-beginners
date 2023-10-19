package com.rockthejvm.part1basics

object Expressions {
  // Expressions
  val meaningOfLife = 40 + 2

  // mathematical expressions: +, -, *, /, bitwise |, &, <<, >>, >>>
  val mathExpressions = 2 + 3 * 4

  // comparison expressions: <, <=, >, >=, ==, !=
  val equalityTest = 1 == 2 // all comparison expressions return a boolean

  // boolean expressions. !, ||, &&. they return a boolean
  val nonEqualityTest = !equalityTest

  // instructions vs expressions:
  //  - expressions are evaluated
  //  - instructions are executed
  // we think in terms of expressions

  // ifs are expressions
  val aCondition = true
  val anIfExpression = if (aCondition) 45 else 99

  // code blocks
  val aCodeBlock = {
    // local values
    val localValue = 89

    // expressions...
    // ...

    // last expression = value of the block
    /* return */ localValue + 54
  }

  // everything in Scala is an expression


  // Without running the code, try to anticipate what the following 3 value will print out
  // Exercise
  val someValue = {
    2 < 3
  }

  // Exercise 2
  val anotherValue = {
    if (someValue) 239 else 999
    42
  }

  // Exercise 3
  val yetAnotherValue = println("Scala")


  def main(args: Array[String]): Unit = {
    //println(meaningOfLife)
    //println(anIfExpression)

    println(someValue) // true
    println(anotherValue) // 42
    println(yetAnotherValue) // it prints "Scala", because in line 53 there is a println. but yetAnotherValue needs also to have a value: ()
  }
}
