package week6

import scala.io.Source

object mnemonics {

  lazy val words = common.loadDictionary filter (_ forall (_.isLetter))
                                                  //> words: => List[String]

  val mnem = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
    '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")
                                                  //> mnem  : scala.collection.immutable.Map[Char,String] = Map(8 -> TUV, 4 -> GHI
                                                  //| , 9 -> WXYZ, 5 -> JKL, 6 -> MNO, 2 -> ABC, 7 -> PQRS, 3 -> DEF)

  /** Invert the mnem map to give a map from chars 'A' ... 'Z' to '2' ... '9' */
  val charCode: Map[Char, Char] =
    for ((digit, str) <- mnem; ltr <- str) yield ltr -> digit
                                                  //> charCode  : Map[Char,Char] = Map(E -> 3, X -> 9, N -> 6, T -> 8, Y -> 9, J -
                                                  //| > 5, U -> 8, F -> 3, A -> 2, M -> 6, I -> 4, G -> 4, V -> 8, Q -> 7, L -> 5,
                                                  //|  B -> 2, P -> 7, C -> 2, H -> 4, W -> 9, K -> 5, R -> 7, O -> 6, D -> 3, Z -
                                                  //| > 9, S -> 7)

  /** Maps a word to the digit string it can represent, e.g. "Java" -> "5282" */
  def wordCode(word: String): String =
    word.toUpperCase map charCode                 //> wordCode: (word: String)String

  /**
   * A map from digit strings to the words that represent them,
   * e.g. "5282" -> List("Java", "Kata", "Lava", ...)
   * Note: A missing number should map to the empty set, e.g. "1111" -> List()
   */
  lazy val wordsForNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue List()//> wordsForNum: => Map[String,Seq[String]]

  /** Return all ways to encode a number as a list of words */
  def encode(number: String): Set[List[String]] =
    if (number.isEmpty) Set(List())
    else {
      for {
        split <- 1 to number.length
        word <- wordsForNum(number take split)
        rest <- encode(number drop split)
      } yield word :: rest
    }.toSet                                       //> encode: (number: String)Set[List[String]]

  //encode("7225247386")
  
  def translate(number: String): Set[String] =
    encode(number) map (_ mkString " ")           //> translate: (number: String)Set[String]
    
  translate("7225247386")                         //> res0: Set[String] = Set(sack air fun, pack ah re to, pack bird to, Scala ir
                                                  //| e to, Scala is fun, rack ah re to, pack air fun, sack bird to, rack bird to
                                                  //| , sack ah re to, rack air fun)
}

object common {

  import java.io.{ File, FileInputStream }

  val dictionaryPath = "/Users/josh/git/scala-class/assignments/hw5/forcomp/src/main/resources/forcomp/linuxwords.txt"

  def loadDictionary = {
    val wordstream = {
      val dictFile = new File(dictionaryPath)
      if (dictFile.exists)
        new FileInputStream(dictFile)
      else
        sys.error("Could not load word list, dictionary file not found")
    }
    try {
      val s = io.Source.fromInputStream(wordstream)
      s.getLines.toList
    } catch {
      case e: Exception =>
        println("Could not load word list: " + e)
        throw e
    } finally {
      wordstream.close()
    }
  }
}