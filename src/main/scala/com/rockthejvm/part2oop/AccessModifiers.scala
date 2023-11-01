package com.rockthejvm.part2oop

object AccessModifiers {

  class Person(val name: String) {
    protected def sayHi(): String = s"Hi, my name is $name." // protected == access to inside the class + children class

    private def watchNetflix(): String = "I'm binge watching my favourite series..." // private: only accessible inside the class
  }

  class Kid(override val name: String, age: Int) extends Person(name) {
    def greetPolitely(): String = // no modifier: "public"
      sayHi() + "I love to play!"
  }

  val aPerson = new Person("Alice")
  val aKid = new Kid("David", 5)


  // COMPLICATION
  class KidWithParents(override val name: String, age: Int, momName: String, dadName: String) extends Person(name){
    val mom = new Person(momName)
    val dad = new Person(dadName)

    // The following 2 lines return an error. See Notion to understand why. I didn't understand this COMPLICATION.
//    def everyoneSayHi(): String =
//      s"Hi, I'm $name, and here are my parents: " + mom.sayHi() + dad.sayHi()
  }

  def main(args: Array[String]): Unit = {
    // println(aPerson.sayHi())
    println(aKid.greetPolitely())
  }
}
