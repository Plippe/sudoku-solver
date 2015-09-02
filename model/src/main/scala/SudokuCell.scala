package com.github.plippe.sudokusolver.model

sealed trait SudokuCell {
  val row: Int
  val column: Int

  val square: Int = {
    val exeption = "must be between 0 and 8"
    (row, column) match {
      case _ if row < 0 || row > 8 => throw new IllegalArgumentException(s"Row $exeption")
      case _ if column < 0 || column > 8 => throw new IllegalArgumentException(s"Column $exeption")
      case _ if column < 3 && row < 3 => 0
      case _ if column < 6 && row < 3 => 1
      case _ if column < 9 && row < 3 => 2
      case _ if column < 3 && row < 6 => 3
      case _ if column < 6 && row < 6 => 4
      case _ if column < 9 && row < 6 => 5
      case _ if column < 3 && row < 9 => 6
      case _ if column < 6 && row < 9 => 7
      case _ if column < 9 && row < 9 => 8
    }
  }

  def +(that: SudokuCell): SudokuCell = {
    (this, that) match {
      case _ if row != that.row => throw new IllegalArgumentException("Rows must match")
      case _ if column != that.column => throw new IllegalArgumentException("Columns must match")
      case (known: SudokuCellKnown, _) => known
      case (_, known: SudokuCellKnown) => known
      case (a: SudokuCellUnknown, b: SudokuCellUnknown) =>
        val values: Set[Int] = a.values.intersect(b.values)
        SudokuCell(a.row, a.column, values)
    }
  }
}

object SudokuCell {
  def apply(row: Int, column: Int, values: Set[Int]): SudokuCell = {
    values.size match {
      case 0 => throw new IllegalArgumentException("No common possible values")
      case 1 => new SudokuCellKnown(row, column, values.head)
      case _ => new SudokuCellUnknown(row, column, values)
    }
  }
}

case class SudokuCellKnown(
  row: Int,
  column: Int,
  value: Int) extends SudokuCell

case class SudokuCellUnknown(
  row: Int,
  column: Int,
  values: Set[Int] = 1.to(9).toSet) extends SudokuCell
