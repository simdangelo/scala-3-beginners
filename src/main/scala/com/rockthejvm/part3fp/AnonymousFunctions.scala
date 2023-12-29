package com.rockthejvm.part3fp

object AnonymousFunctions {

  val doubler: Int => Int = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

    // lambdas = anonymous function instances
  val doubler_v2: Int => Int = (x: Int) => x * 2 // identical to doubler
  val adder: (Int, Int) => Int = (x: Int, y: Int) => x + y // new Function2[Int, Int, Int] {override def apply ...}
  // zero-argument functions
  val justDoSomething: () => Int = () => 45
  val anInvocation = justDoSomething()

  // alternative syntax with curly braces
  val stringToInt = { (str: String) =>
    // implementation
    str.toInt
  }

  val salaries = Seq(20_000, 70_000, 40_000)
  val doubleSalary: Int => Int = (x: Int) => x * 2
  val newSalaries = salaries.map(doubleSalary) // List(40000, 140000, 80000)

  val stringToIntBoring = (str: String) => {
    // code block

  }

  // type inference
  val doubler_v3: Int => Int = (x: Int) => x * 2 // type inferred by compiler
  val adder_v2: (Int, Int) => Int = (x, y) => x + y

  // shortest lambdas
  val doubler_v4: Int => Int = _ * 2
  val adder_v3: (Int, Int) => Int = _ + _


  // Exercise2
  val superAdder = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int) = new Function1[Int, Int] {
      override def apply(y: Int) = x + y
    }
  }
  val superAdder_v2 = (x: Int) => (y: Int) => x + y
  val adding2 = superAdder_v2(2) // (y: Int) >= 2 + y
  val addingInvocation = adding2(43) // 45
  val addingInvocation_v2 = superAdder_v2(2)(45) // the same, but one shot



  def main(args: Array[String]): Unit = {
    println(s"justDoSomething: $justDoSomething")
    println(s"justDoSomething(): ${justDoSomething()}")
  }

}
