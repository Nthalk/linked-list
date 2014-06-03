About
==================

I saw a coding challenge about reversing a linked list with various methods that
made me want to dive into Scala's collection implementations to see if it was 
performant.


Findings
==================

Java has no public singly-linked-list, making it's `Collections.reverse` super
simple.

Scala is smart with it's `Seq`, and `List` implementations, however It does not
do in place mutation (THREAD DANGER).

Additionally, my implementation is much faster than Scala's `LinkedList`
reverse.

One thing to note is that using `@tailrec` yielded better results than my 
iterative approach, but I have yet to optimize that one.   

