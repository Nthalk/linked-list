About
==================

I saw a coding challenge about reversing a linked list with various methods that
made me want to dive into Scala's collection implementations to see if it was 
performant.


Findings
==================

Java has no public singly-linked-list, making it's `Collections.reverse` super
simple.

While Scala is smart with it's `Seq`, and `List`, my implementation is much faster than 
Scala's `LinkedList` reverse.

One thing to note is that all of scala's `reverse` implementations copy the collection
instead of modifying in place. That is definitely the way to go, as concurrency errors
are more obnoxious than OOM's.

However, if you some how can store 1 copy of the list, and you don't want an array,
and you are not sharing it between threads, and you care a whole lot about milliseconds,
and... Yeah, you get it.
