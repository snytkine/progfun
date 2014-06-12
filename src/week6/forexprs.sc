package week6

object forexprs {
  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    for (x <- xs) yield f(x)                      //> mapFun: [T, U](xs: List[T], f: T => U)List[U]

  def flatMap[T, U](xs: List[T], f: T => Iterable[U]): List[U] =
    for (x <- xs; y <- f(x)) yield y              //> flatMap: [T, U](xs: List[T], f: T => Iterable[U])List[U]

  def filter[T](xs: List[T], p: T => Boolean): List[T] =
    for (x <- xs if p(x)) yield x                 //> filter: [T](xs: List[T], p: T => Boolean)List[T]

  case class Book(title: String, authors: List[String])

  val books = Set(
    Book(
      title = "Structure and Interpretation of Computer Programs",
      authors = List("Abelson, Harald", "Sussman, Gerald J.")),
    Book(
      title = "Introduction to Functional Programming",
      authors = List("Bird, Richard", "Wadler, Phil")),
    Book(
      title = "Effective Java",
      authors = List("Bloch, Joshua")),
    Book(
      title = "Effective Java 2",
      authors = List("Bloch, Joshua")),
    Book(
      title = "Java Puzzlers",
      authors = List("Bloch, Joshua", "Gafter, Neal")),
    Book(
      title = "Programming in Scala",
      authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")))
                                                  //> books  : scala.collection.immutable.Set[week6.forexprs.Book] = Set(Book(Eff
                                                  //| ective Java 2,List(Bloch, Joshua)), Book(Programming in Scala,List(Odersky,
                                                  //|  Martin, Spoon, Lex, Venners, Bill)), Book(Structure and Interpretation of 
                                                  //| Computer Programs,List(Abelson, Harald, Sussman, Gerald J.)), Book(Effectiv
                                                  //| e Java,List(Bloch, Joshua)), Book(Introduction to Functional Programming,Li
                                                  //| st(Bird, Richard, Wadler, Phil)), Book(Java Puzzlers,List(Bloch, Joshua, Ga
                                                  //| fter, Neal)))

  // Original
  for (b <- books; a <- b.authors if a startsWith "Bird") yield b.title
                                                  //> res0: scala.collection.immutable.Set[String] = Set(Introduction to Function
                                                  //| al Programming)

  // First substitution
  books.flatMap(b => for (a <- b.authors if a startsWith "Bird") yield b.title)
                                                  //> res1: scala.collection.immutable.Set[String] = Set(Introduction to Function
                                                  //| al Programming)

  // Second substitution
  books.flatMap(b => for (a <- b.authors.withFilter(a => a startsWith "Bird")) yield b.title)
                                                  //> res2: scala.collection.immutable.Set[String] = Set(Introduction to Function
                                                  //| al Programming)
      
  // Third substitution
  books.flatMap(b => b.authors.withFilter(a => a startsWith "Bird").map(_ => b.title))
                                                  //> res3: scala.collection.immutable.Set[String] = Set(Introduction to Function
                                                  //| al Programming)
}