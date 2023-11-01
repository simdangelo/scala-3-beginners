package com.rockthejvm.part2oop

import scala.language.postfixOps

object MethodNotation {

  class Person(val name: String, val age: Int, favoriteMovie: String) {
    infix def likes(movie: String): Boolean =
      movie == favoriteMovie

    infix def +(person: Person): String =
      s"${this.name} is hanging out with ${person.name}"

    // EX.1
    infix def +(nickname: String): Person =
      new Person(s"$name ($nickname)", age, favoriteMovie)

    infix def !!(progLanguage: String) =
      s"$name wonders how can $progLanguage be so cool!"

    // prefix position
    def unary_- : String =
      s"$name's alter ego"

    // EX.2
    def unary_+ : Person =
      new Person(name, age+1, favoriteMovie)

    def isAlive: Boolean = true // the implementation is not important

    def apply(): String = // or def apply(x: Int): String = --> the apply method works regardless of the arguments of the method apply (in that case I would write: println(mary(2)))
      s"Hi, my name is $name and I really enjoy $favoriteMovie"

    def apply(n: Int): String =
      s"$name watched $favoriteMovie $n times."
  }

    val mary = new Person("Mary", 34, "Inception")
    val john = new Person("John", 36, "Fight Club")


    /**
     * EXERCISES:
     *
     * EX.1
     * - a + operator on the Person class that returns a person with a nickname
     * i.e. mary + "the rockstar" => new Person("Marh the rockstar", _, _,) with the same fav movie and the same age
     *
     * EX.2
     * - a UNARY + operator that increases the person's age
     * i.e. +mary => new Person(_, _, age + 1)
     *
     * EX.3
     * - an APPLY method with an int arg
     * i.e. mary.apply(2) => "Mary watched Inception 2 times"
     */

    // for these exercises, I'll add these new methods in the Person Class above


  def main(args: Array[String]): Unit = {
      println(mary.likes("Fight Club"))
      // infix notation - only available for methods with ONE argument
      println(mary likes "Fight Club")

      // "operators" = plain method
      println(mary + john)
      println(mary.+(john))

      println(2 + 3)
      println(2.+(3)) // they're the same

      println(mary !! "Scala")

      // prefix position
      println(-mary)
      println(mary.unary_-) // they're the same

      // postfix notation
      println(mary.isAlive)
      println(mary isAlive) // they're the same

      // apply is special
      println(mary.apply())
      println(mary()) // they're the same

      println("")
      println("EXERCISES")

      //EX.1
      val maryWithNickname = mary + "the rockstar"
      println(maryWithNickname.name)

      // EX.2
      val maryOlder = +mary
      println(maryOlder.age)

      // EX.3
      println(mary(10))
    }
}