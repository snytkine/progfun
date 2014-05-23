package week4

object nth {
  def nth[T](n: Int, xs: List[T]): T =
  	if (xs.isEmpty) throw new IndexOutOfBoundsException
  	else if (n == 0) xs.head
  	else nth(n - 1, xs.tail)                  //> nth: [T](n: Int, xs: week4.List[T])T
  	
  val list = new Cons(1, new Cons(2, new Cons(3, Nil)))
                                                  //> list  : week4.Cons[Int] = (1(2(3.)))
  nth(2, list)                                    //> res0: Int = 3
  
  val list1 = List.apply(1,2)                     //> list1  : week4.List[Int] = (1(2.))
  val list2 = List(1,2)                           //> list2  : week4.List[Int] = (1(2.))
  
  val nil = Zero                                  //> nil  : week4.Zero.type = 0
  val one = nil.successor                         //> one  : week4.Nat = 1+0
  val two = one + one                             //> two  : week4.Nat = 1+1+0
  val thr = two + one                             //> thr  : week4.Nat = 1+1+1+0
  val fou = two + two                             //> fou  : week4.Nat = 1+1+1+1+0
  val fiv = two + thr                             //> fiv  : week4.Nat = 1+1+1+1+1+0
  val dif = fiv - thr                             //> dif  : week4.Nat = 1+1+0
  
  val x = Number(5).eval                          //> x  : Int = 5
}