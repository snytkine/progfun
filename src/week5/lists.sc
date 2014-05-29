package week5

object lists {
  def removeAt[T](n: Int, xs: List[T]) = (xs take n) ++ (xs drop n + 1)
                                                  //> removeAt: [T](n: Int, xs: List[T])List[T]
  def flatten(xs: List[Any]): List[Any] = xs match {
    case Nil => Nil
    case y :: ys => y match {
      case Nil => flatten(ys)
      case z :: zs => z :: flatten(zs) ::: flatten(ys)
      case _ => y :: flatten(ys)
    }
  }                                               //> flatten: (xs: List[Any])List[Any]

  val l1 = List(1, 2, 3, 4, 5)                    //> l1  : List[Int] = List(1, 2, 3, 4, 5)
  removeAt(2, l1)                                 //> res0: List[Int] = List(1, 2, 4, 5)

  val l2 = List(List(1, 1), 2, List(3, List(5, 8)))
                                                  //> l2  : List[Any] = List(List(1, 1), 2, List(3, List(5, 8)))
  flatten(l2)                                     //> res1: List[Any] = List(1, 1, 2, 3, 5, 8)
}