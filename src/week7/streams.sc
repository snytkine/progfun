package week7

object streams {
  val xs = Stream.cons(1, Stream.cons(2, Stream.empty))
                                                  //> xs  : Stream.Cons[Int] = Stream(1, ?)
  Stream(1, 2, 3)                                 //> res0: scala.collection.immutable.Stream[Int] = Stream(1, ?)
  
  1 #:: 2 #:: Stream.empty                        //> res1: scala.collection.immutable.Stream[Int] = Stream(1, ?)
  
  (1 to 1000).toStream                            //> res2: scala.collection.immutable.Stream[Int] = Stream(1, ?)
  
  def streamRange(lo: Int, hi: Int): Stream[Int] = {
    print(lo + " ")
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange(lo + 1, hi))
  }                                               //> streamRange: (lo: Int, hi: Int)Stream[Int]
    
  def listRange(lo: Int, hi: Int): List[Int] =
    if (lo >= hi) Nil
    else lo :: listRange(lo + 1, hi)              //> listRange: (lo: Int, hi: Int)List[Int]
    
  def isPrime(i: Int): Boolean =
    (2 until i) forall (i % _ != 0)               //> isPrime: (i: Int)Boolean
  
  ((1000 to 10000).toStream filter isPrime)(1)    //> res3: Int = 1013
  
  streamRange(1, 10).take(3).toList               //> 1 2 3 res4: List[Int] = List(1, 2, 3)
}