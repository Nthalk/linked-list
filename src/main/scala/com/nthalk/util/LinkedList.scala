package com.nthalk.util

import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec

object LinkedList {
  // O(n) construction using push
  def apply[T](els: T*) = {
    val ret = new LinkedList[T]()
    els.foreach { el => ret.push(el) }
    ret
  }
}
class LinkedList[T] extends Iterable[T] {
  case class Node[T](var el: T, var next: Option[Node[T]] = None)

  private var _tail: Option[Node[T]] = None
  private var _head: Option[Node[T]] = None
  private var _length = 0

  def length = _length

  def iterator: Iterator[T] = {
    var node = _head
    new Iterator[T]() {
      override def hasNext(): Boolean = {
        node.isDefined
      }
      override def next(): T = {
        node match {
          case Some(_node) =>
            val tmp = _node.el
            node = _node.next
            tmp
          case _ =>
            ???
        }
      }
    }
  }

  // O(1)
  def shift: Option[T] = {
    _head match {
      case Some(head) =>
        val ret = Some(head.el)
        _head = head.next
        _length -= 1
        ret
      case _ =>
        None
    }
  }

  // O(1)
  def unshift(t: T): Unit = {
    _length += 1
    _head match {
      case Some(head) =>
        val tmp = _head
        _head = Some(Node(t, tmp))
      case _ =>
        _head = Some(Node(t))
        _tail = _head
    }
  }

  // Full traverse O(n)
  def pop() = {
    var node = _head
    if (node.isEmpty) {
      None
    } else if (_length == 1) {
      _length = 0
      _head = None
      _tail = None
      Some(node.get.el)
    } else {
      _length -= 1
      for (i <- 0 until _length) {
        if (node.isDefined) {
          node = node.get.next
        }
      }
      Some(node.get.el)
    }
  }

  // O(1)
  def push(t: T): Unit = {
    _length += 1
    val node = Some(Node(t))
    _tail match {
      case Some(tail) =>
        tail.next = node
      case _ =>
        _head = node
    }
    _tail = node
  }
  
  // O(n)
  def reverseRecursive: LinkedList[T] = {
    _head match {
      case Some(current) =>
        // Head(A), ..., Current, Next(B), NextNext(B.next)
        // Next(B), Head(A), ..., Current, NextNext(B.next)
        @tailrec def reverse(maybe_old_head: Option[Node[T]], maybe_next: Option[Node[T]]) {
          maybe_next match {
            case Some(next) =>
              // Link forward
              val maybe_next_next = next.next
              // Wire the head
              next.next = maybe_old_head
              reverse(maybe_next, maybe_next_next)
            case _ =>
          }
        }
        reverse(_head, current.next)

        // Fix _head and _tail
        _head = _tail 
        _tail = Some(current)

        // Terminate new tail
        current.next = None
      case _ =>
      // No elements
    }

    this
  }

  // O(n)
  def reverseIterative: LinkedList[T] = {
    _head match {
      case Some(node) =>
        while (node.next.isDefined) {
          // Head(A), ..., Current(Node), Next(B), NextNext(C)
          // Next(B), Head(A), ..., Current(Node), NextNext(C)
          val a = _head
          val b = node.next
          val c = b.get.next
          node.next = c
          b.get.next = a
          _head = b
        }
      case _ =>
    }
    this
  }
}