package week2

import math.abs

object session1 {
  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, f(a) + acc)
    }
    loop(a, 0)
  }                                               //> sum: (f: Int => Int)(a: Int, b: Int)Int

  sum(x => x)(1, 10)                              //> res0: Int = 55

  def sumInts: (Int, Int) => Int = sum(x => x)    //> sumInts: => (Int, Int) => Int
  def sumCubes: (Int, Int) => Int = sum(x => x * x * x)
                                                  //> sumCubes: => (Int, Int) => Int
  sumInts(1, 10)                                  //> res1: Int = 55
  sumCubes(1, 10)                                 //> res2: Int = 3025

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, f(a) * acc)
    }
    loop(a, 1)
  }                                               //> product: (f: Int => Int)(a: Int, b: Int)Int

  def productInts: (Int, Int) => Int = product(x => x)
                                                  //> productInts: => (Int, Int) => Int
  def productCubes: (Int, Int) => Int = product(x => x * x * x)
                                                  //> productCubes: => (Int, Int) => Int
  productInts(1, 10)                              //> res3: Int = 3628800
  productCubes(1, 10)                             //> res4: Int = 520093696

  def factorial(n: Int): Int = product(x => x)(1, n)
                                                  //> factorial: (n: Int)Int

  factorial(5)                                    //> res5: Int = 120

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, combine(f(a), acc))
    }
    loop(a, zero)
  }                                               //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b
                                                  //| : Int)Int

  def su: (Int, Int) => Int = mapReduce(x => x, (x, y) => x + y, 0)
                                                  //> su: => (Int, Int) => Int
  su(1, 5)                                        //> res6: Int = 15

  def prod: (Int, Int) => Int = mapReduce(x => x, (x, y) => x * y, 1)
                                                  //> prod: => (Int, Int) => Int
  prod(1, 5)                                      //> res7: Int = 120

  def fact(n: Int): Int = prod(1, n)              //> fact: (n: Int)Int

  fact(5)                                         //> res8: Int = 120

  val tolerance = 0.0001                          //> tolerance  : Double = 1.0E-4
  def isCloseEnough(x: Double, y: Double): Boolean =
    abs((x - y) / x) / x < tolerance              //> isCloseEnough: (x: Double, y: Double)Boolean
  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }                                               //> fixedPoint: (f: Double => Double)(firstGuess: Double)Double

  fixedPoint(x => 1 + x / 2)(1.0)                 //> res9: Double = 1.999755859375
  
  def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2
                                                  //> averageDamp: (f: Double => Double)(x: Double)Double
  
  def sqrt(x: Double) =	fixedPoint(averageDamp(y => x / y))(1.0)
                                                  //> sqrt: (x: Double)Double
  	
  sqrt(4)                                         //> res10: Double = 2.000000000000002
  sqrt(2)                                         //> res11: Double = 1.4142135623746899
}