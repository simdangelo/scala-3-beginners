package com.rockthejvm.practice

// singly linked list
abstract class LList {
  def head: Int
  def tail: LList
  def isEmpty: Boolean
  def add(element: Int): LList
}

class Empty extends LList{
  override def head: Int = throw new NoSuchElementException
  override def tail: LList = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add(element: Int): LList = new Cons(element, this)
}

class Cons(override val value: Int, override val tail: LList) extends LList {
  override def isEmpty: Boolean = false

  override def add(element: Int): LList = new Cons(element, this)
}

object LListTest {
  def main(args: Array[String]): Unit = {

  }
}
