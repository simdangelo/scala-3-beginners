package com.rockthejvm.part2oop

object AbstractDataTypes {

  abstract class Animal {
    val creatureType: String // abstract
    def eat(): Unit
    // non-abstract fields/methods allowed
    def preferredMeal: String = "anything" // "accessor methods"
  }

  // abstract classes can't be instantiated
//  val anAnimal = new Animal

  // non-abstract classes must implement abstract fields/methods
  class Dog extends Animal {
    override val creatureType: String = "domestic"
    override def eat(): Unit = println("crunching this bone")
    override val preferredMeal: String = "bones" // overriding accessor method (without args/parenthesis) with a field
  }

  val aDog: Animal = new Dog

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class TRex extends Carnivore{
    override def eat(animal: Animal): Unit = println("I'm a T-Rex, I eat animals!")
  }

  //differences between abstract class and trait
  //1. practical difference
  // one class inheritance
  // multiple traits inheritance
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    override def eat(): Unit = println("I'm a croc, I just crunch stuff")
    override def eat(animal: Animal): Unit = println("croc eating animal")
  }

  def main(args: Array[String]): Unit = {

  }
}
