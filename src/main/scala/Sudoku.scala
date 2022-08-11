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

    Vector()

  }


}
