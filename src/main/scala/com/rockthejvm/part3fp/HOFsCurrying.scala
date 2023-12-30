package com.rockthejvm.part3fp

import scala.annotation.tailrec

object HOFsCurrying {

  val aHof: (Int, (Int => Int)) => Int = (x, func) => x + 1
  val anotherHof: Int => (Int => Int) = x => (y => y + 2 * x)

  // quick exercise
  val superfunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = (x, func) => (y => x + y)


//   more examples
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n<=0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x + 1
  val tenThousand = nTimes(plusOne, 10000, 0)

  def nTimes_v2(f: Int => Int, n: Int): Int => Int =
    if (n<=0) (x: Int) => x
    else (x: Int) => nTimes_v2(f, n-1)(f(x))

  val plusTenThousand = nTimes_v2(plusOne, 10000)
  val tenThousand_v2 = plusTenThousand(0)


  def main(args: Array[String]): Unit = {
    println(tenThousand)
    println(tenThousand_v2)
  }

}
