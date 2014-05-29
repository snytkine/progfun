package week5

object listfun {
	val nums = List(1, 9, 3, 0, -4, 7, 2)     //> nums  : List[Int] = List(1, 9, 3, 0, -4, 7, 2)
	val fruits = List("apple", "pineapple", "orange", "banana")
                                                  //> fruits  : List[String] = List(apple, pineapple, orange, banana)
	
	nums filter (x => x > 0)                  //> res0: List[Int] = List(1, 9, 3, 7, 2)
	nums filterNot (x => x > 0)               //> res1: List[Int] = List(0, -4)
	nums partition (x => x > 0)               //> res2: (List[Int], List[Int]) = (List(1, 9, 3, 7, 2),List(0, -4))
	
	nums takeWhile (x => x > 0)               //> res3: List[Int] = List(1, 9, 3)
	nums dropWhile (x => x > 0)               //> res4: List[Int] = List(0, -4, 7, 2)
	nums span (x => x > 0)                    //> res5: (List[Int], List[Int]) = (List(1, 9, 3),List(0, -4, 7, 2))
	
	def pack[T](xs: List[T]): List[List[T]] = xs match {
		case Nil => Nil
		case x :: _ =>
			val (first, rest) = xs span (y => x == y)
			first :: pack(rest)
	}                                         //> pack: [T](xs: List[T])List[List[T]]
	
	val letters = List("a", "a", "a", "b", "c", "c", "a")
                                                  //> letters  : List[String] = List(a, a, a, b, c, c, a)
	pack(letters)                             //> res6: List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(a))
                                                  //| 
	
	def encode[T](xs: List[T]): List[(T, Int)] =
		pack(xs) map (ys => (ys.head, ys.length))
                                                  //> encode: [T](xs: List[T])List[(T, Int)]
		
	encode(letters)                           //> res7: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))

	def concat[T](xs: List[T], ys: List[T]): List[T] =
		(xs foldRight ys)(_ :: _)         //> concat: [T](xs: List[T], ys: List[T])List[T]
		
	concat(List(1,2,3), List(4,5,6))          //> res8: List[Int] = List(1, 2, 3, 4, 5, 6)
	
	def mapFun[T, U](xs: List[T], f: T => U): List[U] =
		(xs foldRight List[U]())(f(_) :: _)
                                                  //> mapFun: [T, U](xs: List[T], f: T => U)List[U]
		
	def lengthFun[T](xs: List[T]): Int =
		(xs foldRight 0)((_, len) => len + 1)
                                                  //> lengthFun: [T](xs: List[T])Int
		
	lengthFun(List(1,2,3))                    //> res9: Int = 3
	mapFun(List(List(1,2,3), List(), List(1,2)), lengthFun)
                                                  //> res10: List[Int] = List(3, 0, 2)
}