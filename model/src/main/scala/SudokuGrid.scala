package com.github.plippe.sudokusolver.model

case class SudokuGrid(
    cells: Seq[SudokuCell]) {

  def +(cell: SudokuCell): SudokuGrid = this + SudokuGrid(Seq(cell))
  def +(cells: SudokuCell*): SudokuGrid = this + SudokuGrid(cells)
  def +(that: SudokuGrid): SudokuGrid = {
    val cells: Seq[SudokuCell] = this.cells ++ that.cells
    val cellsByRowColumn: Iterable[Seq[SudokuCell]] = cells.groupBy { c => s"${c.row}:${c.column}" }.values
    val cellsSummed: Iterable[SudokuCell] = cellsByRowColumn.map(_.reduce(_ + _))

    new SudokuGrid(cellsSummed.toSeq)
  }
}

object SudokuGrid {
  def empty(): SudokuGrid = {
    val cells = for {
      row <- 0.to(8)
      column <- 0.to(8)
    } yield SudokuCellUnknown(row, column)

    SudokuGrid(cells)
  }
}
