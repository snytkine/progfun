package week3

object intsets {
  val t1 = new NonEmpty(3, new Empty, new Empty)  //> t1  : week3.NonEmpty = {.3.}
  val t2 = t1 incl 4                              //> t2  : week3.IntSet = {.3{.4.}}
	val t3 = new NonEmpty(1, new Empty, new Empty)
                                                  //> t3  : week3.NonEmpty = {.1.}
	t3 union t2                               //> res0: week3.IntSet = {{.1.}3{.4.}}
}