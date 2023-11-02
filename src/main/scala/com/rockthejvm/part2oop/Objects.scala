package com.rockthejvm.part2oop

object Objects {

  object MySingleton { // type + the only instance of this type
    val aField = 45

    def aMethod(x: Int) = x + 1
  }


  val theSingleton = MySingleton
  val anotherSingleton = MySingleton
  val isSameSingleton = theSingleton == anotherSingleton // true

  // objects can have fileds and methods
  val theSingletonFied = MySingleton.aField // or TheSingleton.aField
  val theSingletonMethodCall = MySingleton.aMethod(99)

  class Person(name: String){
    def sayHi(): String = s"Hi, my name is $name"
  }

  // COMPANIONS = class + object with the same name in the same file
  object Person{ // it is called COMPANION OBJECT
    val N_EYES = 2
    def canFly(): Boolean = false
  }

  val mary = new Person("Mary")
  val mary_v2 = new Person("Mary")
  val marysGreeting = mary.sayHi()

  val humansCanFly = Person.canFly()
  val nEyesHiman = Person.N_EYES


  // equality
  // 1. equality of reference
  val sameMary = mary eq mary_v2 //false -> different instances
  val sameSingleton = MySingleton eq MySingleton // true

  // 2. equality of "sameness" - in Java defined as equals
  val sameMary_v2 = mary equals mary_v2 // false
  val sameMary_v3 = mary == mary_v2 // same as equals - false
  val sameSingleton_vw = MySingleton == MySingleton // true


  // objects can extends classes
  object BigFoot extends Person("Big Foot")

  // Scala Application = object + main
  /*
    public class Objects {
      public static void main(String[] args) {...}
  }
   */
  def main(args: Array[String]): Unit = {
    println(isSameSingleton)

    println(sameMary_v2)
    println(sameMary_v3)
  }
}
