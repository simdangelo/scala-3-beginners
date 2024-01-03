package com.rockthejvm.part4power
import com.rockthejvm.practice
import com.rockthejvm.practice.{ConsGenerics, EmptyGenerics, LListGenerics}

object AllThePatterns {

  object MySingleton

  // 1 - constants
  val someValue: Any = "Scala"
  val constants = someValue match {
    case 42 => "a number"
    case "Scala" => "THE Scala"
    case true => "the truth"
    case MySingleton => "a singleton object"
  }

  // 2 - match anything
  val matchAnythingVar = 2 + 3 match {
    case something => s"I've matched anything, it's $something."
  }
  val matchAnything = someValue match {
    case _ => "I can match anything at all"
  }

  // 3 - tuple
  val aTuple = (1,4)
  val matchTuple = aTuple match {
    case (1, somethingElse) => s"A tuple with 1 and $somethingElse"
    case (something, 2) => "A tuple with 2 as its second field"
  }

  // PM structures can be nested
  val nestedTuple = (1, (2, 3))
  val matchNesteTuple = nestedTuple match {
    case (_, (2, v)) => "A nested tuple ..."
  }

  // 4 - case classes
  val aList: LListGenerics[Int] = ConsGenerics(1, ConsGenerics(2, EmptyGenerics()))
  val matchList = aList match {
    case EmptyGenerics() => "an empty list"
    case ConsGenerics(head, ConsGenerics(_, tail)) => s"a non-empty list starting with $head"
  }

  val anOption: Option[Int] = Option(2)
  val matchOption = anOption match {
    case None => "an empty option"
    case Some(value) => "non-empty, got $value"
  }

  // 5 - list patterns
  val aStandardList = List(1,2,3,42)
  val matchStandardList = aStandardList match {
    case List(1, _, _, _) => "list with 4 elements, first is 1"
    case List(1, _*) => "list starting with 1"
    case List(1, 2, _) :+ 42 => "list ending in 42"
    case 1::tail => "list starting with 1 with access to tail"
    case head::tail => "deconstructed list"
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val matchTyped = unknown match {
    case anInt: Int => s"I matched an Int, I can add to to it: ${anInt + 2}"
    case aString: String => "I matched a string"
    case _: Double => "I matched a double I don't care about."
  }

  // 7 - name binding
  val bindingNames = aList match {
    case ConsGenerics(head, rest @ ConsGenerics(_, tail)) => s"can use $rest"
  }

  // 8 - chained patterns
  /*
  switch(myValue) {
    case1:
    case2:
      run some code
   */
  val multiMatch = aList match {
    case EmptyGenerics() | ConsGenerics(0, _) => "an empty list to me"
    case _ => "anything else"
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case ConsGenerics(_, ConsGenerics(specialElement, _)) if specialElement > 5 => "second element is big enough"
    case _ => "anything else"
  }

  /**
   * Example
   */
  val aSimpleInt = 45
  val isEven_bad = aSimpleInt match {
    case n if n % 2 == 0 => true
    case _ => false
  }
//   heavy anti pattern
  val isEven_bad_v2 = if (aSimpleInt % 2 == 0 ) true else false

//   better
  val isEven = aSimpleInt % 2 == 0

  /**
   *
   * Exercise (trick for who has no experience with JVM language)
   */
  val numbers: List[Int] = List(1,2,3,4)
  val numbersMatch = numbers match {
    case listOfString: List[String] => "a list of strings"
    case listOfInts: List[Int] => "a list of numbers"
  }


  def main(args: Array[String]): Unit = {
    println(constants)
//    println(matchAnythingVar)
//    println(matchAnything)
    println(numbersMatch)
  }

}
