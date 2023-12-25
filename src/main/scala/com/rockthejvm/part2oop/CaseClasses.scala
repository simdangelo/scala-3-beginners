package com.rockthejvm.part2oop

object CaseClasses {

  case class Person(name: String, age: Int) {
    //    do some other stuff
  }

  // PROPERTIES OF clase class

  // 1 - class args are now fields
  val daniel = new Person("Daniel", 99)
  val danielsage = daniel.age

  // 2 - toString, equals and hasCode
  val danielToString = daniel.toString

  val danielDuped = new Person("Daniel", 99)
  val isSameDaniel = daniel == danielDuped

  // 3 - utility methods
  val danielYounger = daniel.copy(age = 78) // new Person("Daniel", 78)

  // 4 - case classes have companion objects
  val thePersonSingleton = Person
  val daniel_v2 = Person("Daniel", 99) // constructor

  // 5 - CCs are serializable
  // use case: Akka

  // 6 - CCs have extractor patterns for PATTERN MATCHING

//  case class CCWithNoArgs { <- illegal declaration!
//    // some code
//  }

//  case class CCWithArgListNoArgs() // <- legal
  case class CCWithArgListNoArgs[A]() // <- legal, mainly in the context of generics




  case object UnitedKingdom {
    // fields and methods
    def name: String = "The UK"
  }


  def main(args: Array[String]): Unit = {
    println(danielToString)
    println(isSameDaniel)
  }

}
