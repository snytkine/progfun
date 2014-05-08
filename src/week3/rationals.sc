package week3

object rationals {
  val x = new Rational(1, 3)                      //> x  : week3.Rational = 1/3
  val y = new Rational(5, 7)                      //> y  : week3.Rational = 5/7
  val z = new Rational(3, 2)                      //> z  : week3.Rational = 3/2
  x.numer                                         //> res0: Int = 1
  x.denom                                         //> res1: Int = 3
  x - y - z                                       //> res2: week3.Rational = -79/42
  y + y                                           //> res3: week3.Rational = 10/7
  x < y                                           //> res4: Boolean = true
  x max y                                         //> res5: week3.Rational = 5/7
  x * y                                           //> res6: week3.Rational = 5/21
  x / y                                           //> res7: week3.Rational = 7/15
  -x                                              //> res8: week3.Rational = 1/-3
  new Rational(2)                                 //> res9: week3.Rational = 2/1
}

class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nonzero")
  
  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  val numer = x / gcd(x, y)
  val denom = y / gcd(x, y)

  def < (that: Rational) = this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) = if (this < that) that else this

  def + (that: Rational) =
		new Rational(
			this.numer * that.denom + that.numer * this.denom,
			this.denom * that.denom)

  def unary_- = new Rational(-this.numer, this.denom)

  def - (that: Rational) = this + -that

  def * (that: Rational) =
    new Rational(this.numer * that.numer, this.denom * that.denom)
    
  def / (that: Rational) = this * new Rational(that.denom, that.numer)

  override def toString = this.numer + "/" + this.denom
}