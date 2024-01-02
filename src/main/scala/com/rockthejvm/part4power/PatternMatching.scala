package com.rockthejvm.part4power

import scala.util.Random

object PatternMatching {

  // switch on steroids
  val random = new Random()
  val aValue = random.nextInt(100)

  val description = aValue match {
    case 1 => "the first"
    case 2 => "the second"
    case 3 => "the third"
    case _ => s"something else: $aValue"
  }

  // decomposes values - case classes are great candidates for this
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 16)

  val greeting = bob match {
    case Person(n, a) if a < 18 => s"Hi, my name is $n and I'm $a years old."
    case Person(n, a) => s"Hello there, my name is $n and I'm not allowed to say my age."
    case _ => "I don't know who I am."
  }


  // pattern matching on sealed hierarchy
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Cat(meowStyle: String) extends Animal

  val anAnimal: Animal = Dog("Terra Nova")
  val animalPM = anAnimal match {
    case Dog(someBreed) => "I've detected a dog."
    case Cat(meow) => "I've detected a cat"
  }

  /**
   *
   * Exercise
   * - show(Sum(Number(2), Number(3))) this should return "2 + 3"
   * - show(Sum(Sum(Number(2), Number(3)), Number(4))) this should return "2 + 3 + 4"
   * - show(Prod(Sum(Number(2), Number(3)), Number(4))) this should return "(2 + 3) * 4"
   * - show(Sum(Prod(Number(2), Number(3)), Number(4))) this should return "2 * 3 + 4"
   */

  sealed trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(expr: Expr): String = expr match {
    case Number(n) => s"$n"
    case Sum(left, right) => show(left) + " + " + show(right)
    case Prod(left, right) => {
      def maybeShowParentheses(expr: Expr) = expr match {
        case Prod(_, _) => show(expr)
        case Number(_) => show(expr)
        case Sum(_, _) => s"(${show(expr)})"
      }

      maybeShowParentheses(left) + " * " + maybeShowParentheses(right)
    }
  }

  def main(args: Array[String]): Unit = {
    println(greeting)
    println(show(Sum(Number(2), Number(3))))
    println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
    println(show(Prod(Sum(Number(2), Number(3)), Number(4))))
    println(show(Sum(Prod(Number(2), Number(3)), Number(4))))
  }
}
