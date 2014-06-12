package week6

object test {
	val xs = Array(1, 2, 3, 44)               //> xs  : Array[Int] = Array(1, 2, 3, 44)
	xs map (x => x * 2)                       //> res0: Array[Int] = Array(2, 4, 6, 88)
	
	val s = "Hello World"                     //> s  : String = Hello World
	s filter (c => c.isUpper)                 //> res1: String = HW

	s exists (c => c.isUpper)                 //> res2: Boolean = true
	s forall (c => c.isUpper)                 //> res3: Boolean = false
	
	val pairs = List(1, 2, 3) zip s           //> pairs  : List[(Int, Char)] = List((1,H), (2,e), (3,l))
	pairs.unzip                               //> res4: (List[Int], List[Char]) = (List(1, 2, 3),List(H, e, l))
	
	s flatMap (c => List('.', c))             //> res5: String = .H.e.l.l.o. .W.o.r.l.d
	
	xs.sum                                    //> res6: Int = 50
	xs.max                                    //> res7: Int = 44
	
	(1 to 5) flatMap (x => (1 to 10) map (y => (x, y)))
                                                  //> res8: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,1), (1,2
                                                  //| ), (1,3), (1,4), (1,5), (1,6), (1,7), (1,8), (1,9), (1,10), (2,1), (2,2), (2
                                                  //| ,3), (2,4), (2,5), (2,6), (2,7), (2,8), (2,9), (2,10), (3,1), (3,2), (3,3), 
                                                  //| (3,4), (3,5), (3,6), (3,7), (3,8), (3,9), (3,10), (4,1), (4,2), (4,3), (4,4)
                                                  //| , (4,5), (4,6), (4,7), (4,8), (4,9), (4,10), (5,1), (5,2), (5,3), (5,4), (5,
                                                  //| 5), (5,6), (5,7), (5,8), (5,9), (5,10))
	
	def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
		(xs zip ys).map { case (x, y) => x * y }.sum
                                                  //> scalarProduct: (xs: Vector[Double], ys: Vector[Double])Double
}