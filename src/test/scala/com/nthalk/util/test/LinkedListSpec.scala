package com.nthalk.util.test
import org.scalatest._
import com.nthalk.util.LinkedList
import scala.collection.mutable.ListBuffer

class LinkedListSpec extends FlatSpec with Matchers {
  "A Linked List" should "be able to unshift elements into it" in {
    val ll = LinkedList[Int]()
    ll.length should be(0)
    ll.unshift(1)
    ll.length should be(1)
  }

  it should "be able to shift elements off of it" in {
    val ll = LinkedList[Int]()
    ll.length should be(0)
    ll.unshift(1)
    ll.length should be(1)
    ll.shift should be(Some(1))
    ll.length should be(0)
  }

  it should "be able to shift multiple elements off of it" in {
    val ll = LinkedList[Int]()
    ll.length should be(0)
    ll.unshift(1)
    ll.unshift(2)
    ll.length should be(2)
    ll.shift should be(Some(2))
    ll.shift should be(Some(1))
    ll.length should be(0)
  }
  
  it should "be able to push elements onto it" in {
    val ll = LinkedList[Int]()
    ll.push(1)
    ll.length should be(1)
    ll.push(2)
    ll.length should be(2)
    ll.pop should be(Some(2))
    ll.length should be(1)
    ll.pop should be(Some(1))
    ll.length should be(0)
  }

  it should "be able to pop elements off of it" in {
    val ll = LinkedList[Int]()
    ll.length should be(0)
    ll.unshift(1)
    ll.length should be(1)
    ll.pop should be(Some(1))
    ll.length should be(0)
  }

  it should "be able to pop multiple elements off of it" in {
    val ll = LinkedList[Int]()
    ll.length should be(0)
    ll.unshift(1)
    ll.unshift(2)
    ll.length should be(2)
    ll.pop should be(Some(1))
    ll.length should be(1)
    ll.pop should be(Some(2))
    ll.length should be(0)
  }

  it should "be iterable" in {
    val ll = LinkedList[Int](1)
    val itr = ll.iterator
    itr.hasNext should be(true)
    itr.next should be(1)
    itr.hasNext should be(false)
  }

  it should "be move the iterator" in {
    val ll = LinkedList[Int](1, 2)
    val itr = ll.iterator
    itr.hasNext should be(true)
    itr.next should be(1)
    itr.next should be(2)
    itr.hasNext should be(false)
  }

  it should "be convertable to a list" in {
    val ll = LinkedList(1, 2, 3, 4, 5)
    ll.toList should be(List(1, 2, 3, 4, 5))
  }
  
  it should "be reversable iterably" in {
    for(i <- 0 to 10){
      val list = (i to 10).toList
      LinkedList(list:_*).reverseIterative.toList should be(list.reverse)
    }    
  }
  
  it should "be reversable recursivly" in {
    for(i <- 0 to 10){
      val list = (i to 10).toList
      LinkedList(list:_*).reverseRecursive.toList should be(list.reverse)
    }    
  }

}