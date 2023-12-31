package com.rockthejvm.part3fp

object MapFlatMapFilterFor {
  val aList = List(1, 2, 3) // [1] -> [2] -> [3] -> Empty (Nil) // [1,2,3]

  val firstElement = aList.head
  val restOfElement = aList.tail

  // map
  val anIncrementedList = aList.map(_ + 1)

  // filter
  val onlyOddNumbers = aList.filter(_ % 2 != 0)

  // flatMap
  val toPair: Int => List[Int] = (x: Int) => List(x, x + 1)
  val aFlatMappedList = aList.flatMap(toPair)


  // all the possibile combinations of all the elements of those lists, in the format "1a - black"
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white", "red")

  val combinations = numbers.flatMap(number => chars.flatMap(char => colors.map(color => s"$number$char - $color")))
  /*
  `lambda = num => chars.map(char => s”$num$char”)`
  `[1,2,3,4].flatMap(lambda)`
  `lambda(1) = chars.map(char => s“1$char”) = [”1a”, “1b”, “1c”, “1d”]`
  `lambda(2) = chars.map(char => s“2$char”) = [”2a”, “2b”, “2c”, “2d”]`
  `lambda(3) = chars.map(char => s“3$char”) = [”3a”, “3b”, “3c”, “3d”]`
  `lambda(4) = chars.map(char => s“4$char”) = [”4a”, “4b”, “4c”, “4d”]`
   */

  // my alternative
  val partialCombination = chars.flatMap(char => colors.map(color => s"$char - $color"))
  val combinations_v2 = numbers.flatMap(number => chars.flatMap(char => colors.map(color => s"$number$char - $color")))


  // for- comprehension
  val combinationFor = for {
    number <- numbers // each of these statement is called Generator
    char <- chars
    color <- colors
  } yield s"$number$char - $color"

  // the same, but only for even numbers
  val combinationsEven = numbers.filter(_ % 2 == 0).flatMap(number => chars.flatMap(char => colors.map(color => s"$number$char - $color")))
  val combinationForEven = for {
    number <- numbers if number % 2 == 0
    char <- chars
    color <- colors
  } yield s"$number$char - $color"

  // for-comprehension with Unit
  // if foreach

  /**
   *
   *
   */

  //  EXERCISE1
  import com.rockthejvm.practice.*

  val lSimpleNumbers: LListGenerics[Int] = ConsGenerics(1, ConsGenerics(2, ConsGenerics(3, EmptyGenerics())))
  // map, flatMap, filter
  val incrementedNumbers = lSimpleNumbers.map(_ + 1)
  val filteredNumbers = lSimpleNumbers.filter(_ % 2 == 0)
  val toPairLList: Int => LListGenerics[Int] = (x: Int) => ConsGenerics(x, ConsGenerics(x + 1, EmptyGenerics()))
  val flatMappedNumbers = lSimpleNumbers.flatMap(toPairLList)

  // map + flatMap -> eligible for for-comprehension
  val combinationNumber = for {
    number <- lSimpleNumbers if number % 2 == 0
    char <- ConsGenerics('a', ConsGenerics('b', ConsGenerics('c', EmptyGenerics())))
  } yield s"$number-$char"

  def main(args: Array[String]): Unit = {
    println(combinations)
    //    println(partialCombination)
    //    println(combinations_v2)
    println(combinationFor)
    println(combinationsEven)
    println(combinationForEven)

    numbers.foreach(println)
    for {
      num <- numbers
    } println(num)

    println(s"incrementedNumbers: $incrementedNumbers")
    println(s"filteredNumbers: $filteredNumbers")
    println(s"flatMappedNumbers: $flatMappedNumbers")
    println(combinationNumber)
  }
}
