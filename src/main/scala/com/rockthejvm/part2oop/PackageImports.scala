package com.rockthejvm.part2oop

val meaningOfLife = 42
def computeMyLife: String = "Scala"

object PackageImports { // top-level

  // packages = "folders"

  // fully qualified name
  val aList: com.rockthejvm.practice.LListGenerics[Int] = ??? // throws notImplementedError

  // import
  import com.rockthejvm.practice.LListGenerics
  val anotherList: LListGenerics[Int] = ???
  // alias to avoid name collision
  import java.util.{List as JList}
  val aJavaList: JList[Int] = ???

  // import everything
  import com.rockthejvm.practice.*
  val aPredicate: ConsGenerics[Int] = ???

  // import multiple symbols
  import PhysicsConstants.{SPEED_OF_LIGHT, EARTH_GRAVITY}
  val c = SPEED_OF_LIGHT

  // import everything but something
  object PlayingPhysics {
    import PhysicsConstants.{PLANK as _, *}
//    val plank = PLANK <- will not work
  }

  import com.rockthejvm.part2oop.* // import the meaningOfLife and computeMyLife
  val mol = meaningOfLife

  // default imports
  // scala.*, scala.Predef.*, java.lang.*

  // exports
  class PhysicsCalculator {
    import PhysicsConstants.*
    def computePhotonEnergy(wavelenght: Double): Double =
      PLANK/wavelenght
  }

  object ScienceApp {
    val physicsCalculator = new PhysicsCalculator

    export physicsCalculator.computePhotonEnergy

    def computeEquivalentMass(wavelength: Double): Double =
      computePhotonEnergy(wavelength) / (SPEED_OF_LIGHT*SPEED_OF_LIGHT)
  }

  def main(args: Array[String]): Unit = {

  }
}

object PhysicsConstants {
  val SPEED_OF_LIGHT = 299792458
  val PLANK = 6.62e-34 // 6.62 * 10^(-34)
  val EARTH_GRAVITY = 9.8
}
