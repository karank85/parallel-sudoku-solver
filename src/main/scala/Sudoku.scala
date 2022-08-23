import scala.collection.parallel.CollectionConverters._

object Sudoku extends App {

  def solve(board: String): Vector[String] = {

    def cartesian(a: Vector[Char], b: Vector[Char]): Vector[Vector[String]] = a.map(r => b.map(c => s"$r$c"))

    val rows = ('A' to 'I').toVector
    val columns = ('1' to '9').toVector
    val rowPeers = cartesian(rows,columns)
    val allSquares = rowPeers.flatten
    val colPeers = columns.map(c => rows.map(r => s"$r$c"))
    val pairRows = Vector(('A' to 'C').toVector, ('D' to 'F').toVector, ('G' to 'I').toVector)
    val pairCols = Vector(('1' to '3').toVector, ('4' to '6').toVector, ('7' to '9').toVector)
    val boxPeers = pairCols.flatMap(pc => pairRows.map(pr => cartesian(pr,pc).flatten))
    val unitPeers = rowPeers ++ colPeers ++ boxPeers
    val mpUnits = Map() ++ allSquares.map(sq => (sq,unitPeers.filter(_.contains(sq))))
    val mpPeers = Map() ++ mpUnits.map(e => (e._1, e._2.flatten.toSet - e._1))

    def parseBoard: Map[String, String] = {
      val boardVec = board.map(b => b.toString).toVector
      allSquares.zip(boardVec).toMap
    }

    def parseGrid: Map[String, String] = {

      val mp = allSquares.map(sq => sq -> "123456789").toMap
      //val filtered = parseBoard.filter((s ,d) => "123456789".contains(d))
      val filtered = parseBoard.filter(d => "123456789".contains(d._2) && !assign(mp, d._1, d._2))
      if filtered.size != parseBoard.size then throw Exception("Can't assign") else mp

    }

    def assign(values: Map[String, String], s: String, d: String): Boolean = ???

    Vector()

  }

  solve("003020600900305001001806400008102900700000008006708200002609500800203009005010300")
  solve("4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......")


}
