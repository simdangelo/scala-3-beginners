package com.rockthejvm.practice

import scala.annotation.tailrec

object TuplesMapsExercises {

  /**
   *
   *
   */
  // ["Simone", ("Matteo", "Fabio")]
  def addPerson(network: Map[String, Set[String]], newPerson: String): Map[String, Set[String]] =
    network + (newPerson -> Set())

  def removePerson(network: Map[String, Set[String]], personToRemove: String): Map[String, Set[String]]  =
    network - personToRemove

  def removePerson2(network: Map[String, Set[String]], personToRemove: String): Map[String, Set[String]] =
    (network - personToRemove).map(pair => (pair._1, pair._2 - personToRemove))

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    if (!network.contains(a)) throw new IllegalArgumentException(s"The person $a is not part of the network")
    else if (!network.contains(b)) throw new IllegalArgumentException(s"The person $b is not part of the network")
    else {
      val friendsOfA = network(a)
      val friendsOfB = network(b)

      network + (a -> (friendsOfA + b)) + (b -> (friendsOfB + a))
    }
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    if (!network.contains(a) || !network.contains(b)) network
    else {
      val friendsOfA = network(a)
      val friendsOfB = network(b)

      network + (a -> (friendsOfA - b)) + (b -> (friendsOfB - a))
    }
  }

  def nfriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) -1
    else network(person).size
  }

  def mostFriends(network: Map[String, Set[String]]): String = {
    if (network.isEmpty) throw new RuntimeException("Network is empty!")
    else {
      /*
      Map(Bob -> Set(Mary), Mary -> Set(Bob, Jim), Jim -> Set(Mary))
      ("", -1), (Bob, [Mary]) => (Bob, 1)
      (Bob, 1), (Mary, [Bob, Jim]) => (Mary, 2)
      (Mary, 2), (Jim, [Mary]) => (Mary, 2)
      (Mary, 2)
       */
      val best = network.foldLeft(("", -1)) ((currentBest, newAssociation) => {
        val currentMostPopularPerson = currentBest._1
        val mostFriendsSoFar = currentBest._2

        val newPerson = newAssociation._1
        val newPersonFriends = newAssociation._2.size

        if (mostFriendsSoFar < newPersonFriends) (newPerson, newPersonFriends)
        else currentBest
      })

      best._1
    }
  }

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    network.count(pair => pair._2.isEmpty)
  }


  // hard exercise
  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    /*
    Map(Bob -> Set(Mary), Mary -> Set(Bob, Jim), Jim -> Set(Mary))
     */
    @tailrec
    def search(discoveredPeople: Set[String], consideredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        val personsFriends = network(person)

        if (personsFriends.contains(b)) true
        else search(discoveredPeople - person ++ personsFriends -- consideredPeople, consideredPeople + person)
      }
    }
    if (!network.contains(a) || !network.contains(b)) false
    else search(network(a), Set(a))
    }


  def main(args: Array[String]): Unit = {
    val empty: Map[String, Set[String]] = Map()

    val network = addPerson(addPerson(empty, "Bob"), "Mary")
    println(network)
    val network_2 = friend(network, "Bob", "Mary")
    println(network_2)
    val network_3 = unfriend(network, "Bob", "Mary")
    println(network_3)

    val network_4 = addPerson(addPerson(addPerson(empty, "Bob"), "Mary"), "Jim")
    val network_5 = friend(friend(network_4, "Bob", "Mary"), "Jim", "Mary")
    println(network_5)
    println(s"Bob's friends number: ${nfriends(network_5, "Bob")}")
    println(s"Mary's friends number: ${nfriends(network_5, "Mary")}")
    println(s"Jim's friends number: ${nfriends(network_5, "Jim")}")
    println(s"Daniel's friends number: ${nfriends(network_5, "Daniel")}")

    println(s"Who has the highest number of friends: ${mostFriends(network_5)}")
    println(s"Number of people with no friends: ${nPeopleWithNoFriends(network_5)}")
    println(s"Number of people with no friends after adding Daniel: ${nPeopleWithNoFriends(addPerson(network_5, "Daniel"))}")

    println(s"Is there a connection (direct or indirect) between Bob and Jim? ${socialConnection(network_5, "Bob", "Jim")}")
    println(s"Is there a connection (direct or indirect) between Bob and Daniel? ${socialConnection(addPerson(network_5, "Daniel"), "Bob", "Daniel")}")
    }

}
