package com.rockthejvm.part2oop

object AnonymousClasses {

  abstract class Animal {
    def eat(): Unit
  }

  class SomeAnimal extends Animal {
    override def eat(): Unit = println("I' a weird animal!")
  }

  val someAnimal = new SomeAnimal

  val someAnimal_2 = new Animal {
    override def eat(): Unit = println("I' a weird animal!")
  }
  /*
  equivalent with:

  AnonymousClasses.AnonClass$1 extends Animal {
    override def eat(): Unit = println("I' a weird animal!")
  }
  val someAnimal_2 = new AnonymousClasses.AnonClass$1
   */


  // works for classes (abstract or not) + traits
  class Person(name: String) {
    def sayHi(): Unit = println(s"My name is $name")
  }

  val jim = new Person("Jim") {
    override def sayHi(): Unit = println("MY NAME IS JIM!")
  }


  def main(args: Array[String]): Unit = {

  }
}
