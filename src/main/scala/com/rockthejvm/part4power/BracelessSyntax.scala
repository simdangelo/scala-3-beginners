package com.rockthejvm.part4power

object BracelessSyntax {

  val anIfexpression = if (2 > 3) "bigger" else "smaller"

  val anIfexpression_v2 =
    if (2 < 3) {
      "bigger"
    } else {
      "smaller"
    }

  // compact
  val anIfexpression_v3 =
    if (2 < 3) "bigger"
    else "smaller"


  // scala 3
  val anIfexpression_v4 =
    if 2 > 3 then
      "bigger"
    else
      "smaller"

  val anIfexpression_v5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  // scala3 one-liner
  val anIfexpression_v6 = if 2 > 3 then "bigger" else "smaller"


  //  for-comprehension
  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  // sala3
  val aForComprehension_v2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  // pattern matching
  val meaningOfLife = 42
  val aPatternMatching = meaningOfLife match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"
  }

  // scala 3
  val aPatternMatching_v2 = meaningOfLife match
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"


  // methods without braces
  def computeMeaningOfLife(arg: Int): Int = {
    val partialResult = 40

    partialResult + 2
  }


  // class definition with sgnificant indentation (same for traits, objects, enums etc)
  class Animal {
    def eat(): Unit = println("I'm eating.")
  }

  class Animal_v2:
    def eat(): Unit = println("I'm eating.")
    def grow(): Unit = println("I'm growing.")
  //      ...
  // 3000 lines of code
  //      ...
  end Animal_v2

  val aSpecialAnimal = new Animal_v2:
    override def eat(): Unit = println("I'm special.")



  def main(args: Array[String]): Unit = {
    println(anIfexpression_v5)
  }

}
