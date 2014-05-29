package week5

object higherOrderListFuncs {
	def squareList(xs: List[Int]): List[Int] = xs match {
		case Nil => Nil
		case y :: ys => y * y :: squareList(ys)
	}                                         //> squareList: (xs: List[Int])List[Int]
	
	def squareList2(xs: List[Int]): List[Int] =
		xs map (x => x * x)               //> squareList2: (xs: List[Int])List[Int]
		
	def nums = List(-2, -1, 0, 1, 2, 3, 4)    //> nums: => List[Int]
	squareList(nums)                          //> res0: List[Int] = List(4, 1, 0, 1, 4, 9, 16)
	squareList2(nums)                         //> res1: List[Int] = List(4, 1, 0, 1, 4, 9, 16)
	
	def posElems(xs: List[Int]): List[Int] = xs match {
		case Nil => Nil
		case y :: ys => if (y > 0) y :: posElems(ys) else posElems(ys)
	}                                         //> posElems: (xs: List[Int])List[Int]
	
	def posElems2(xs: List[Int]): List[Int] =
		xs filter (x => x > 0)            //> posElems2: (xs: List[Int])List[Int]
	
	posElems(nums)                            //> res2: List[Int] = List(1, 2, 3, 4)
	posElems2(nums)                           //> res3: List[Int] = List(1, 2, 3, 4)
}