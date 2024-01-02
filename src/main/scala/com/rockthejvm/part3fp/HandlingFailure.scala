package com.rockthejvm.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure {

  val aTry: Try[Int] = Try(42)
  val aFailedTry: Try[Int] = Try(throw new RuntimeException())

  // alternative construction
  val aTry_v2: Try[Int] = Success(42)
  val aFailedTry_v2: Try[Int] = Failure(new RuntimeException)

  // main API
  val checkSuccess = aTry.isSuccess // boolean
  val checkFailure = aTry.isFailure // boolean
  val aChain = aFailedTry.orElse(aTry)

  // map, flatMap, filter, for-comprehension
  val anIncremenedTry = aTry.map(_ + 1)
  val aFlatMappedTry = aTry.flatMap(mol => Try(s"My meaning of  life is $mol"))
  val aFilteredTry = aTry.filter(_ % 2 == 0)


  // why: avoid unsafe APIs which can throw exceptions
  def unsafeMethod(): String =
    throw new RuntimeException("no string for you, buster!")
  // defensive: try-catch-finally
  val stringLengthDefensive = try {
    val aString = unsafeMethod()
    aString.length
  } catch {
    case e: RuntimeException => -1
  }

  // purely functional
  val stringLengthPure = Try(unsafeMethod()).map(_.length).getOrElse(-1)


  // DESIGN
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException("no string for you, buster!"))
  def betterBackupMethod(): Try[String] = Success("Scala")
  val stringLengthPure_v2 = betterUnsafeMethod().map(_.length)
  val aSafeChain = betterUnsafeMethod().orElse(betterBackupMethod()).map(_.length)


  /**
   *
   * Exercise:
   *  - obtain a connection
   *  - then fetch the url
   *  - then print the resulting HTML
   */

  val host = "localhost"
  val port = "8081"
  val myDesiredURL = "rockthejvm.com/home"

  class Connection {
    val random = new Random()

    def get(url: String): String = {
      if (random.nextBoolean()) "<html>Success</html>"
      else throw new RuntimeException("Cannot fetch page right now.")
    }

    def getSafe(url: String): Try[String] =
      Try(get(url))
  }

  object HttpsService {
    val random = new Random()

    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Cannot access host/port combination.")

    def getConnectionSafe(host: String, port: String): Try[Connection] =
      Try(getConnection(host, port))
  }

  // defensive style
  val connection = try {
    val conn = HttpsService.getConnection(host, port)
    val html = try {
      conn.get(myDesiredURL)
    } catch {
      case e: RuntimeException => s"<html>${e.getMessage}</html>"
    }
  } catch {
    case e: RuntimeException => s"<html>${e.getMessage}</html>"
  }

  // purely functional approach
  val maybeConn = Try(HttpsService.getConnection(host, port))
  val maybeHtml = maybeConn.flatMap(conn => Try(conn.get(myDesiredURL)))
  val finalResult = maybeHtml.fold(e => s"<html>${e.getMessage}</html>", s => s)

  // for-comprehension
  val maybeHtml_v2 = for {
    conn <- HttpsService.getConnectionSafe(host, port)
    html <- conn.getSafe(myDesiredURL)
  } yield html



  def main(args: Array[String]): Unit = {
//    println(maybeHtml)
    println(finalResult)

  }

}
