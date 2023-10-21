package com.rockthejvm.part1basics

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recursion {

  // "repetition" = recursion

  // sum numbers until the number passed as argument

  def sumUntil(n: Int): Int =
    if (n <= 0) 0
    else n + sumUntil(n - 1)

  def sumUntil_v2(n: Int): Int = {
    /*
    sut(10, 0) =
    sut(9, 10+0) =
    sut(8, 9+10) =
    sut(7, 8+9+10) =
    ...
    sut(0, 1+2+3+4+...+9+10) =
     */
    @tailrec
    def sumUntilTailrec(x: Int, accumulator: Int): Int =
      if (x <= 0) accumulator
      else sumUntilTailrec(x - 1, accumulator + x)
    // no further stack frames are necessary = no more risk of StackOverflowError.

    sumUntilTailrec(n, 0)
  }

  // STACK RECURSION
  def sumNumbersBetween(a: Int, b: Int): Int = {
    if (a > b) 0
    else a + sumNumbersBetween(a + 1, b)
    /*
      snb(3,7) =
      3 + snb(4, 7) = 3 + 4 + snb(5, 7) = 3 + 4 + 5 + snb(6, 7) = 3+4+5+6+snb(7,7) = 3+4+5+6+7+snb(8,7) = 3+4+5+6+7
     */
  }

  // TAIL RECURSION
  def sumNumbersBetween_v2(a: Int, b: Int): Int = {
    @tailrec
    def sumTailrec(currentNumber: Int, accumulator: Int): Int =
      if (currentNumber > b) accumulator
      else sumTailrec(currentNumber + 1, currentNumber + accumulator)

    sumTailrec(a, 0)
  }


  /**
   * EXERCISES
   * 1. concatenate a string n times
   * 2. fibonacci function, tail recursive
   * 3. is isPrime function tail recursive or not?
   */

  // 1. concatenate a string n times
  def concatenate(string: String, n: Int): String = {
    @tailrec
    def concatTailrec(remainingTimes: Int, accumulator: String): String =
      if (remainingTimes <= 0) accumulator
      else concatTailrec(remainingTimes - 1, string + accumulator)

    concatTailrec(n, "")
  }

  //2. fibonacci function, tail recursive
  def fibonacci(n: Int): Int = {
    @tailrec
    def fiboTailrec(i: Int, last: Int, previous: Int): Int =
      if (i >= n) last
      else fiboTailrec(i + 1, last + previous, last)

    if (n <= 2) 1
    else fiboTailrec(2, 1, 1)
  }

  // 3. is isPrime function tail recursive or not? yes, it is but let's rephrase in order to make it more visibile
  def isPrime(n: Int): Boolean = {
   @tailrec
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else if (n % t == 0) false
      else isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  def main(args: Array[String]): Unit = {
    //println(sumUntil(200000))
    println(sumUntil_v2(100000))
    println(sumNumbersBetween_v2(5, 6))
    println(concatenate("Scala", 10))
    println(fibonacci(10))
  }
}
