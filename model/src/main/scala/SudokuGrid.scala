package com.github.plippe.sudokusolver.model

case class SudokuGrid(
  cells: Seq[SudokuCell])

object SudokuGrid {
  def empty(): SudokuGrid = {
    val cells = for {
      row <- 0.to(8)
      column <- 0.to(8)
    } yield SudokuCellUnknown(row, column)

    SudokuGrid(cells)
  }
}
