package com.rockthejvm.part3fp

object TuplesMaps {
// tuples: finite ordered "lists" / group of values under the same "big" value
  val aTuple = (2, "rock the jvm") // Tuple2[Int, String] == (Int, String)
  val firstField = aTuple._1
  val aCopiedTuple = aTuple.copy(_1 = 54)

  // tuples of 2 elements
  val aTuple_v2 = 2 -> "rock the jvm" // identical to (2, "rock the jvm")

  // maps: keys -> values
  val aMap = Map()
  val phonebook = Map(
    "Kim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123
  ).withDefaultValue(-1)

  // core API
  val phonebookHasDaniel = phonebook.contains("Daniel") // checking for keys
  val marysPhoneNumber = phonebook("Mary") // or phonebook("Mary") -> crash with an exception

  // add pair
  val newPair = "Mary" -> 678
  val newPhonebook = phonebook + newPair // new map
  // remove a key
  val phonebookWithoutDaniel = phonebook - "Daniel" // new map

  // turn linear collections into map and viceversa
  // list -> map
  val linearPhonebook = Seq(
    "Kim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123
  )
  val phonebook_v2 = linearPhonebook.toMap

  // map -> linear collection
  val linearPhonebook_v2 = phonebook.toList // or toSeq, toVector, toArray, toSet

  // map, flatMap, filter
  // Map("Jim" -> 123, "jiM" -> 999) => Map("JIM" -> ???)
  val aProcessedPhonebook = phonebook.map(pair => (pair._1.toUpperCase(), pair._2))

  // filtering keys
  val noJs = phonebook.view.filterKeys(!_.startsWith("J"))//.toMap

  // mapping values
  val prefixNumbers = phonebook.view.mapValues(number => s"0255-$number").toMap

  // other collections can create maps
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  val nameGroupings = names.groupBy(name => name.charAt(0)) // Map[Char, List[String]]


  def main(args: Array[String]): Unit = {
    println(aTuple)
    println(firstField)
    println(aCopiedTuple)

    println(phonebook)
    println(phonebookHasDaniel)
    println(marysPhoneNumber)

    println(nameGroupings)
  }

}
