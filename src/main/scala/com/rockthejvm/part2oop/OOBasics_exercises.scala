package com.rockthejvm.part2oop

object OOBasics_exercises {

  /**
   * EXERCISE1: imagine we're creating a backed for a book publishing house.
   * Create a Novel and a Writer class.
   *
   * Writer: first name, surname, year (constructor arguments)
   * - method fullname
   *
   * Novel: name, year of release, author (constructor arguments)
   * - method author age at the time of novel publishing (subtract the year of birth from year of release of the novel)
   * - method isWrittenBy(author) (boolean)
   * - method copy (new year of release) = new instance of Novel (for example if you are lunching a second edition of the book you want to keep all the data execpt the new year of release
   */

  class Writer(firstName: String, lastName: String, val yearOfBirth: Int) {
    def fullname(): String =
      s"$firstName $lastName"
  }

  class Novel(title: String, yearOfRelease: Int, val author: Writer) {
    def authorAge(): Int = yearOfRelease - author.yearOfBirth // the problem is that yearOfBirth is not accessible from the author because it's not a field. So add "val" keyword to "yearOfBirth" in the list of Writer class parameters to makes it an accessible field

    def isWrittenBy(author: Writer): Boolean = author == this.author

    def copy(newYear: Int): Novel = new Novel(title, newYear, author)
  }

  val primoLevi = new Writer("Primo", "Levi", 1919)
  val charlesDickens = new Writer("Charles", "Dickens", 1812)
  val aNovel = new Novel("Great Expectations", 1861, charlesDickens)
  val newEditionNovel = aNovel.copy(1871)


  /**
   * EXERCISE2: an immutable counter class (use case: if you are writing a distributed we application which keeps track of user logins)
   *  - constructed wiht an initial count
   *  - increment and decrement methods => NEW instance of counter (because we only have val that are constants)
   *  - increment(n)/decrement(n) methods => NEW instance of counter
   *  - print()
   */

  class Counter(count: Int = 0) {
    def increment(): Counter =
      new Counter(count + 1)

    def decrement(): Counter =
      if (count == 0) this
      else new Counter(count - 1)

    def print(): Unit =
      println(s"Current count: $count")

    def increment(n: Int): Counter =
      if (n <= 0) this
      else new Counter(count + n) // or increment().increment(n-1) -> this alternative solution is vulnerable so StackOverflowError

    def decrement(n: Int): Counter =
      if (n <= 0) this
      else new Counter(count - n) // or decrement().decrement(n-1)
  }

  val aCounter = new Counter()


  def main(args: Array[String]): Unit = {
    // EXERCISE 1
    println(primoLevi.fullname())
    println(charlesDickens.fullname())
    println(aNovel.authorAge())
    println(aNovel.isWrittenBy(primoLevi))
    println(aNovel.isWrittenBy(charlesDickens))
    println(newEditionNovel.author.fullname())
    println(newEditionNovel.authorAge())

    // EXERCISE 2
    aCounter.print() //o
    aCounter.increment().print()
    aCounter.increment(200000).print()

  }
}

