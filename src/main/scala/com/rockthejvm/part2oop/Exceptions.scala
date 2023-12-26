package com.rockthejvm.part2oop

object Exceptions {

  val aString: String = null
  // aString.length crasches with a NPE (NullPointerException)

  // 1 - throw exceptions
//  val aWeirdValue: Int = throw new NullPointerException

  // type Throwable
  // Error, e.g. SOError, OOMError
  // Exception, e.g. NPException, NSEException, ...


  def getInt(withException: Boolean): Int =
    if (withException) throw new NullPointerException // RuntimeException("No int for you")
    else 42

  val potentialFail = try {
    // code that may fail
    getInt(true) // an Int
  } catch {
    // most specific exceptions first
    case e: RuntimeException => 54
    case e: NullPointerException => 35
      // ...
  }

  // custom exception
  class MyException extends RuntimeException {
    // fields or methods
    override def getMessage: String = "MY EXCEPTION"
  }

  val myException = new MyException

  /**
   *
   * 1 - crash with StackOverflowError (SOError)
   * 2 - crash with OutOfMemoryError (OOMError)
   * 3 - find an element matching a predicate in LList
   */

  // Exercise1 - StackOverflowError
  def soCrash(): Unit  = {
    def infinite(): Int = 1 + infinite()

    infinite()
  }


  // Exercise2 - OutOfMemoryError
  def oomCrash(): Unit = {
    def bigString(n: Int, acc: String): String =
      if (n==0) acc
      else bigString(n-1, acc+acc)

    bigString(50000000, "Scala")
  }


  def main(args: Array[String]): Unit = {
//    println(aString.length)
//    println(potentialFail)
//    val throwingMyException = throw myException

//    soCrash()
    oomCrash()
  }

}
