package com.rockthejvm.part3fp

import scala.util.Random

object Options {

  val anOption: Option[Int] = Option(42)
  val anEmptyOption: Option[Int] = Option.empty
  // alternative version
  val aPresentValue: Option[Int] = Some(4)
  val anEmptyOption_v2: Option[Int] = None

  // standard API
  val isEmpty = anOption.isEmpty
  val innerValue = anOption.getOrElse(98) // obtain the option value if it is not empty, otherwise take 98
  val anotherOption = Option(46)
  val aChainedOption = anEmptyOption.orElse(anotherOption)

  val anIncrementedOption = anOption.map(_ + 1) // Some(43)
  val aFilteredOption = anIncrementedOption.filter(_ % 2 == 0) // none
  val aFlatMappedOption = anIncrementedOption.flatMap(value => Option(value * 10)) // Some(430)


  // WHY options: work with unsafe API
  def unsafeMethod(): String = null
  def fallbackMethod(): String = "some valid result"

  // defensive style
  val stringLength = if (unsafeMethod() == null) -1 else unsafeMethod().length

  // option defensive style
  val stringLengthOption = Option(unsafeMethod()).map(_.length)


  // use case for orElse
  val someResult = Option(unsafeMethod()).orElse(Option(fallbackMethod()))

  // DESIGN
  def betterUnsafeMethod(): Option[String] = None
  def betterFallbackMethod(): Option[String] = Some("A valid result")
  val betterChain = betterUnsafeMethod().orElse(betterFallbackMethod())

  // example: Map.get
  val phonebook = Map(
    "Daniel" -> 1234
  )

  val marysPhoneNumber = phonebook.get("Mary") // None
  // no need to crash, check for nulls or if Mary is present in the Map


  /**
   * Exercise
   * Get the host and port from the config map
   * 1. try to open a connection
   * 2. print “Conn successful”
   * 3. or “Conn failed”
   */

  val config: Map[String, String] = Map(
    // comes from elsewhere
    "host" -> "176.45.32.1",
    "port" -> "8081"
  )

  class Connection {
    def connect(): String = "Connection successful" // this method is your gateway to the server that you want to establish some connection with
  }

  object Connection {
    val random = new Random()

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // defensive style
  /*
  String host = config("host")
  String port = config("port")
  if (host != null)
    if (port != null)
      Connection conn = Connection.apply(host, port)
      if (conn!=null)
        return conn.connect()
   */

  val host = config.get("host")
  val port = config.get("port")

  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  val connStatus = connection.map(_.connect())

  // compact
  val connStatus_v2 =
    config.get("host").flatMap(h =>
      config.get("port").flatMap(p =>
        Connection(h, p).map(_.connect())
      )
    )

    // for-comprehension
    val connStatus_v3 = for {
      h <- config.get("host")
      p <- config.get("port")
      conn <- Connection(h, p)
    } yield conn.connect()



  def main(args: Array[String]): Unit = {

    println(connStatus.getOrElse("Failed to establish connection."))
    println(connStatus_v2.getOrElse("Failed to establish connection."))
  }

}
