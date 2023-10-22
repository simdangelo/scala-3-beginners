package com.rockthejvm.part1basics

object StringOps {

  val aString: String = "Hello, I am learning Scala"

  val secondChar = aString.charAt(1)
  val firstWord = aString.substring(0, 5)
  val words = aString.split(" ") // array("Hello,", "I", "am", "learning", "scala")
  val startsWithHello = aString.startsWith("Hello") //true
  val allDashes = aString.replace(' ', '-')
  val allUppercase = aString.toUpperCase() // also to toLowercase()
  val nChars = aString.length

  // other functions
  val reversed = aString.reverse
  val aBunchOfChars = aString.take(10)
  // parse to numeric
  val numberAsString = "2"
  val number = numberAsString.toInt

  // interpolations
  val name = "Alice"
  val age = 12
  val greeting = "Hello, I'm " + name + " and I am " + age + " years old."
  val greeting_v2 = s"Hello, I'm $name and I am $age years old."
  val greeting_v3 = s"Hello, I'm $name and I will be turning ${age + 1} years old."

  // f-interpolation
  val speed = 1.2f // float
  val myth = f"$name can eat $speed%2.2f burgers per minute."

  // raw-interpolation
  val escapes = "This is a \n newline"
  val escapes2 = raw"This is a \n newline"

  def main(args: Array[String]): Unit = {
    println(secondChar)
    println(firstWord)
    println(words)
    println(startsWithHello)
    println(allDashes)
    println(allUppercase)
    println(nChars)

    //other languages
    println(reversed)
    println(aBunchOfChars)
    println(number)

    // s-string
    println(greeting_v2)
    println(greeting_v3)

    // f-string
    println(myth)

    // raw-interpolation
    println(f"escapes: $escapes")
    println(f"escapes2: $escapes2")
  }
}
