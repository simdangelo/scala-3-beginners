package com.rockthejvm.part2oop

object Enums {

  enum Permission {
    case READ, WRITE, EXECUTE, NONE

    // fields and methods
    def openDocument(): Unit =
      if (this==READ) println("opening document...")
      else println("reading not allowed")
  }

  val somePermissions: Permission = Permission.READ

  // constructor arguments
  enum PermissionWithBits(bits: Int) {
    case READ extends PermissionWithBits(4) // 100
    case WRITE extends PermissionWithBits(2) // 010
    case EXECUTE extends PermissionWithBits(1) // 001
    case NONE extends PermissionWithBits(0) // 000
  }

  object PermissionWithBits {
    def fromBits(bits: Int): PermissionWithBits =
      PermissionWithBits.NONE
  }


  // standard APi
  val somePermissionOrdinal = somePermissions.ordinal
  val allPermission = PermissionWithBits.values
  val readPermission: Permission = Permission.valueOf("READ")


  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionOrdinal)
    println(allPermission)
    println(readPermission)
  }

}
