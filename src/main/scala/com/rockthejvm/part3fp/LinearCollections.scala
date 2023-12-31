package com.rockthejvm.part3fp

import scala.util.Random

object LinearCollections {

  def testSeq(): Unit = {
    val aSequence = Seq(4, 7, 5)

    // main API: index an element
    val thirdElement = aSequence.apply(2)
    // other methods
    val reverse = aSequence.reverse
    val concatenation = aSequence ++ Seq(5, 6, 7)
    val sortedSequence = aSequence.sorted // [1,2,3,4]

    // map/flatMap/filter/for
    val anIncrementedSequence = aSequence.map(_ + 1)
    val aFlatMappedSequence = aSequence.flatMap(x => Seq(x, x + 1)) // [4,5,7,8,5,6]
    val aFilteredSequence = aSequence.filter(_ % 2 == 0) // [4]

    val sum = aSequence.foldLeft(0)(_ + _) // 16
    val stringRep = aSequence.mkString("[", ",", "]")

    println(s"aSequence: $aSequence")
    println(s"thirdElement: $thirdElement")
    println(s"reverse: $reverse")
    println(s"concatenation: $concatenation")
    println(s"sortedSequence: $sortedSequence")

    println(s"anIncrementedSequence: $anIncrementedSequence")
    println(s"aFlatMappedSequence: $aFlatMappedSequence")
    println(s"aFilteredSequence: $aFilteredSequence")

    println(s"sum: $sum")
    println(s"stringRep: $stringRep")
  }

  // lists
  def testLists(): Unit = {
    val aList = List(1, 2, 3)
    // same API as Seq

    val firstElement = aList.head
    val rest = aList.tail

    // appending and prepending
    val aBiggerList = 0 +: aList :+ 4
    val prepending = 0 :: aList // equivalent to Cons in our LListGenerics

    val scalax5 = List.fill(5)("Scala")
  }

  // ranges
  def testRanges(): Unit = {
    val aRange = 1 to 10
    val aNonInclusiveRange = 1 until 10 // 10 not included
    // same Seq API
    (1 to 10).foreach(_ => println("Scala"))
  }

  def testArrays(): Unit = {
    val anArray = Array(1,2,3,4,5,6)
    // most Seq APIs
    // arrays are not Seqs
    val aSequence: Seq[Int] = anArray.toIndexedSeq

    // arrays are mutable
    anArray.update(2, 30)
  }

  // vectors: fast Seqs for large amount of data
  def testVectors(): Unit = {
    val aVector: Vector[Int] = Vector(1,2,3,4,5,6)
    // the same Seq API
  }

  def smallBenchmark(): Unit = {
    val maxRuns = 1000
    val maxCapacity = 1000000

    def getWriteTime(collections: Seq[Int]): Double = {
      val random = new Random()
      val times = for {
        i <- 1 to maxRuns
      } yield {
        val index = random.nextInt(maxCapacity)
        val element = random.nextInt()

        val currentTime = System.nanoTime()
        val updatedCollection = collections.updated(index, element)
        System.nanoTime() - currentTime
      }

      // compute average
      times.sum * 1.0 / maxRuns // or times.foldLeft(0L)(_ + _) * 1.0 / maxRuns
    }

    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector

    val listCase = getWriteTime(numbersList)
    val vectorCase = getWriteTime(numbersVector)

    println(s"List case: $listCase")
    println(s"Vector case: $vectorCase")
  }

  // sets
  def testSets(): Unit = {
    val aSet = Set(1,2,3,4,5,4) // no ordering guaranteed
    //equals + hashCode = hash set
    // main API: test if in the set
    val contains3 = aSet.contains(3) // true
    val contains3_v2 = aSet.apply(3) // or more compact: aSet(3)
    // adding/removing
    val aBiggerSet = aSet + 4 // new set
    val aSmallerSet = aSet - 4 // new set
    // concatenation
    val anotherSet = Set(4,5,6,7,8)
    val muchBiggerSet = aSet.union(anotherSet)
    val muchBiggerSet_v2 = aSet ++ anotherSet // same
    val muchBiggerSet_v3 = aSet | anotherSet // same (alias for union)
    // difference
    val aDiffSet = aSet.diff(anotherSet)
    val aDiffSet_v2 = aSet -- anotherSet
    // intersection
    val anIntersection = aSet.intersect(anotherSet) // [4,5]
    val anIntersection_v2 = aSet & anotherSet
  }


  def main(args: Array[String]): Unit = {
    //    testSeq()
//    testRanges()
//    smallBenchmark()
    println(testSets())
  }


}
