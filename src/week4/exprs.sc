package week4

object exprs {
  Sum(Number(5), Number(9)).show                  //> res0: String = 5 + 9
  Sum(Prod(Number(2), Var("x")), Var("y")).show   //> res1: String = 2 * x + y
  Prod(Sum(Number(2), Var("x")), Var("y")).show   //> res2: String = (2 + x) * y
  true match {
    case true =>
      val x = 3
      val y = 5
      x * y
    case false => 10
  }                                               //> res3: Int = 15
}