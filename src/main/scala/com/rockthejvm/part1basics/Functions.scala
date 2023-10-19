package com.rockthejvm.part1basics

object Functions {

  def aFunction(a: String, b: Int): String = {
    a + " " + b // ONE expression
  }
  // function invocation
  val aFunctionInvocation = aFunction("Scala", 99)

  // functions with no arguments
  def aNoArgFunction(): Int = 45
  def aParameterlessFunction: Int = 45

  // functions can be recursive
  def stringConcatenation(str: String, n: Int): String = {
    if (n == 0) ""
    else if (n == 1) str
    else str + stringConcatenation(str, n - 1)
  }
  val scalax3 = stringConcatenation("Scala", 3)

  def aVoidFunction(aString: String): Unit =
    println(aString)

  def computeDoubleStringWithSideEffect(aString: String): String = {
    aVoidFunction(aString) //unit
    aString + aString // meaningful value
  } // side-effect + return meaningful value: DISCOURAGED in FP

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction (n, n + 1)
  }

  /*
  Exercises
  1. A greeting function (name, age) => "Hi my name is $nome and I am $age years old"
  2. Factorial function => n => 1*2*3*..*n
  3. Fibonacci function => fib(1)=1, fib(2)=1, fib(3)=1+1, fib(n)=fib(n-1) + fib(n-2)
  4. Test if a number is prime
   */

  //1
  def greetingFunction(name: String, age: Int) =
    s"Hi, my name is $name and I am $age years old."

  //2
  def factorialFunction(n: Int): Int = {
    if (n<=0) 0
    else if (n==1) 1
    else n*factorialFunction(n-1)
  }

  //3
  def fibonacciFunction(n: Int): Int = {
    if (n<=0) 0
    else if (n<=2) 1
    else fibonacciFunction(n-1) + fibonacciFunction(n-2)
  }

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t<=1) true
      else n % t != 0 && isPrimeUntil(t-1)

    isPrimeUntil(n/2)
  }


  def main(args: Array[String]): Unit = {
    println(aFunctionInvocation)
    println(scalax3)

    println(greetingFunction("Simone", 28))
    println(factorialFunction(5))
    println(fibonacciFunction(5))
    println(isPrime(18))
  }
}
