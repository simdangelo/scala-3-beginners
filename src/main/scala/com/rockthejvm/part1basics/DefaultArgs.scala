package com.rockthejvm.part1basics

import scala.annotation.tailrec

object DefaultArgs {

  @tailrec
  def sumUntilTailrec(x: Int, accumulator: Int = 0): Int =
    if (x <= 0) accumulator
    else sumUntilTailrec(x - 1, accumulator + x)

  val sumUntil100 = sumUntilTailrec(10)

  def savePicture(dirPath: String, name: String, format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit =
    println(s"Saving picture in $format in path $dirPath")

  def main(args: Array[String]): Unit = {
    println(sumUntil100)

    // default values areinjected
    savePicture("users/simone/photos/", "myphoto")
    // pass explicit different values for default args
    savePicture("users/simone/photos/", "myphoto", "png")
    // pass values after the default argument
    savePicture("users/simone/photos/", "myphoto", width = 800, height = 600)
    // naming arguments allow passing in a different orders
    savePicture("users/simone/photos/", "myphoto", height = 800, width = 600)
  }
}
