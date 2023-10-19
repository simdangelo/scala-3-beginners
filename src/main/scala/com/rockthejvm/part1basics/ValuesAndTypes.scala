package com.rockthejvm.part1basics

object ValuesAndTypes {

  // fundamental bit of Scala language: Value
  val meaningOfLife: Int = 42
  // reassigning is not allowed!

  val anInteger = 67 // Int is optional. Type Inference

  val aBoolean = true
  val aChar: Char = 'a'
  val aShort: Short = 4 // 2 bytes
  val anInt: Int = 78 // 4 bytes
  val aLong: Long = 53245 // 8 bytes
  val aFloat: Float = 4.3f // 4 bytes
  val aDouble: Double = 4.32 // 8 bytes

  val aString: String = "Scala"

  def main(args: Array[String]): Unit = {

  }
}
