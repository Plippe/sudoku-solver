package com.github.plippe.sudokusolver.model

import scala.annotation.tailrec

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

  def solved(): SudokuGrid = {
    def removeKnowValue(cells: Seq[SudokuCell], original: SudokuCellKnown): SudokuGrid = {
      def removeValue(cell: SudokuCellUnknown) =
        SudokuCell(cell.row, cell.column, cell.values - original.value)

      val unknownCells: Seq[SudokuCellUnknown] = cells
        .filter(_.isInstanceOf[SudokuCellUnknown])
        .map(_.asInstanceOf[SudokuCellUnknown])

      SudokuGrid(unknownCells.filter(_.row == original.row).map(removeValue)) +
        SudokuGrid(unknownCells.filter(_.column == original.column).map(removeValue)) +
        SudokuGrid(unknownCells.filter(_.square == original.square).map(removeValue))
    }

    def couldValueBeElsewhere(cells: Seq[SudokuCell], value: Int, original: SudokuCellUnknown): Boolean = {
      val unknownCells: Seq[SudokuCellUnknown] = cells
        .filter(_.isInstanceOf[SudokuCellUnknown])
        .map(_.asInstanceOf[SudokuCellUnknown])
        .filterNot(_ == original)

      unknownCells.exists { cell => !cell.values.contains(value) && cell.row == original.row } ||
        unknownCells.exists { cell => !cell.values.contains(value) && cell.column == original.column } ||
        unknownCells.exists { cell => !cell.values.contains(value) && cell.square == original.square }
    }

    @tailrec def recursive(grid: SudokuGrid): SudokuGrid = {
      val grids: Seq[SudokuGrid] = grid.cells.map {
        case cell: SudokuCellKnown => removeKnowValue(grid.cells, cell)
        case cell: SudokuCellUnknown =>
          val optValue: Option[Int] = cell.values.find(couldValueBeElsewhere(grid.cells, _, cell))
          val newCell: SudokuCell = optValue.fold[SudokuCell](cell) { value => SudokuCellKnown(cell.row, cell.column, value) }
          SudokuGrid(Seq(newCell))
      }

      grid + grids.reduce(_ + _) match {
        case newGrid if !newGrid.cells.exists(_.isInstanceOf[SudokuCellUnknown]) => newGrid
        case newGrid if newGrid == grid => newGrid
        case newGrid => recursive(newGrid)
      }
    }

    val gridWithAllCells = SudokuGrid.empty + this
    recursive(gridWithAllCells)
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
