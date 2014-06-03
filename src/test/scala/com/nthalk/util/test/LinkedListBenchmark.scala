package com.nthalk.util.test

import org.scalameter.PerformanceTest
import scala.collection.parallel.mutable.ParTrieMap
import scala.collection.parallel.ForkJoinTaskSupport
import org.scalameter.Gen
import java.util.Collections
import com.nthalk.util.LinkedList
import org.scalameter.Executor

object LinkedListBenchmark extends PerformanceTest.Quickbenchmark {

  val sizes = Gen.range("size")(10000, 40000, 800000)
  val ranges = for {
    size <- sizes
  } yield 0 until size

  performance of "scala.collection.mutable.LinkedList" in {
    measure method ("reverse") in {
      using(ranges.map { range =>
        scala.collection.mutable.LinkedList(range: _*)
      }) in { list =>
        list.reverse.head
      }
    }
  }

  performance of "scala.collection.Seq" in {
    measure method ("reverse") in {
      using(ranges.map { range =>
        Seq(range: _*)
      }) in { list =>
        list.reverse.head
      }
    }
  }

  performance of "Collections.reverse(new java.util.LinkedList(r))" in {
    measure method ("reverse") in {
      using(ranges.map { range =>
        val collection = scala.collection.JavaConversions.asJavaCollection(range)
        new java.util.LinkedList(collection)
      }) in { list =>
        Collections.reverse(list)
        list.getFirst()
      }
    }
  }

  performance of "LinkedList" in {
    measure method ("reverseIterative") in {
      using(ranges.map { range =>
        LinkedList(range: _*)
      }) in { list =>
        list.reverseIterative.head
      }
    }
    measure method ("reverseRecursive") in {
      using(ranges.map { range =>
        LinkedList(range: _*)
      }) in { list =>
        list.reverseRecursive.head
      }
    }
  }

}