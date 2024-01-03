package com.rockthejvm.part4power

object ImperativeProgramming {

  val meaningOfLife: Int = 42 // immutable

  var aVariable = 99
  aVariable = 100 // cars can be reassigned

  aVariable += 10 // aVariable  = aVariable + 10

  // increment/decrement operators don't exist in Scala
  //  aVariable++ // illegal in Scala


  // loops
  def testLoops(): Unit = {
    var i = 0
    while (i < 10) {
      println(s"Counter at ${i}")
      i += 1
    }
  }

  val anExpression = aVariable += 10
  val aLoop = while (aVariable < 130) {
    println("counting more")
    aVariable += 1
  }


  def main(args: Array[String]): Unit = {
    testLoops()
  }

}
