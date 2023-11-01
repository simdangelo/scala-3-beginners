package com.rockthejvm.part2oop

object Inheritance {

  class Animal {
    val creatureType = "wild"

    def eat(): Unit = println("This animal is eating.")
  }

  class Cat extends Animal {
    def crunch(): Unit = {
      eat()
      println("crunch, crunch")
    }
  }

  val cat = new Cat

  class Person(val name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age) // must specify super-constructor

  class Dog extends Animal {
    override val creatureType: String = "domestic"

    override def eat(): Unit = println("mmmm, I like this bone.")

    override def toString: String = "a dog"
  }

  //val dog = new Dog
  val dog: Animal = new Dog


  // overloading vs overriding
  class Crocodile extends Animal {
    override val creatureType = "very wild"

    override def eat(): Unit = println("I can eat anything, I'm a croc.")

    // overloading: multiple methods with the same name, different signature
    // different signature =
    //    different argument list (different number of args + different arg types) (mandatory to be considered overloading)
    //    + different return type (optional)
    def eat(animal: Animal): Unit = println("I'm eating this poor fella.")

    /*
    EXERCISE: which of the following statements are considered LEGAL OVERLOADING?

      def eat(dog: Dog): Unit = println("eating a dog") VALID! it takes Dog as argument instead of Animal
      def eat(person: Person): Unit = println(s"I'm eating a human with the name {$person.name}") VALID. for the same reason above
      def eat(person: Person, dog: Dog): Unit = println("I'm eating a human AND the dog") VALID! it takes 2 arguments
      def eat(): Int = 45 NOT VALID! Crocodile class already has an eat() method taking no arguments!
      def eat(dog: Dog, person: Person): Unit = println("I'm eating a human AND the dog") VALID! Why? The whole goal of overload validation is not confuse the compiler whenever you call the eat() method. So, although we already have an eat() method that takes Dog and Person instances as arguments (in this order), the compiler will have no problem finding the right overload if I call eat() with a Person and Dog (in this order) and If call eat() with a Dog and a Person (in this order). So:
     */
  }

  def main(args: Array[String]): Unit = {
    cat.eat()
    cat.crunch()

    dog.eat()
    println(dog) // println(dog.toString)
  }
}
