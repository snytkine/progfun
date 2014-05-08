package week1

import scala.annotation.tailrec

object session {
  def not(x: Boolean): Boolean = if (x) false else true
                                                  //> not: (x: Boolean)Boolean
  def and(x: Boolean, y: => Boolean): Boolean = if (x) y else false
                                                  //> and: (x: Boolean, y: => Boolean)Boolean
  def or(x: Boolean, y: => Boolean): Boolean = if (x) true else y
                                                  //> or: (x: Boolean, y: => Boolean)Boolean

  not(true)                                       //> res0: Boolean = false
  not(false)                                      //> res1: Boolean = true

  and(true, true)                                 //> res2: Boolean = true
  and(true, false)                                //> res3: Boolean = false
  and(false, true)                                //> res4: Boolean = false
  and(false, false)                               //> res5: Boolean = false

  or(true, true)                                  //> res6: Boolean = true
  or(true, false)                                 //> res7: Boolean = true
  or(false, true)                                 //> res8: Boolean = true
  or(false, false)                                //> res9: Boolean = false

  def loop: Boolean = loop                        //> loop: => Boolean

  and(false, loop)                                //> res10: Boolean = false
  or(true, loop)                                  //> res11: Boolean = true

  def abs(x: Double): Double = if (x < 0) -x else x
                                                  //> abs: (x: Double)Double
  def sqrt(x: Double): Double = {
    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    def isGoodEnough(guess: Double): Boolean =
      abs(guess * guess - x) / x < 0.001

    def improve(guess: Double): Double =
      (guess + x / guess) / 2

    sqrtIter(1.0)
  }                                               //> sqrt: (x: Double)Double

  sqrt(2)                                         //> res12: Double = 1.4142156862745097

  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)              //> gcd: (a: Int, b: Int)Int

  gcd(14, 21)                                     //> res13: Int = 7

  def factorial(n: Int): Int = {
    def loop(n: Int, acc: Int): Int =
      if (n == 0) acc
      else loop(n - 1, n * acc)
    loop(n, 1)
  }                                               //> factorial: (n: Int)Int

  factorial(5)                                    //> res14: Int = 120
}