package com.github.plippe.sudokusolver.model

import org.scalatest.FunSpec

class TestSudokuGrid extends FunSpec {
  describe("SudokuGrid") {
    describe("empty") {
      val grid = SudokuGrid.empty()

      it("should create a grid with 81 (9 rows, 9 columns) elements, one for each cell") {
        assert(grid.cells.length === 81)
        for { r <- 0.to(8); c <- 0.to(8) } {
          assert(grid.cells.exists { cell => cell.row == r && cell.column == c })
        }
      }

      it("should create a grid with unknown cells") {
        grid.cells.foreach { cell =>
          assert(cell.isInstanceOf[SudokuCellUnknown])
        }
      }
    }

    describe("sum") {
      val empty = SudokuGrid.empty()

      it("should sum cells to return at most one element per cell") {
        val grid = empty +
          SudokuCellUnknown(0, 0, Set(1, 2, 3)) +
          SudokuCellUnknown(0, 0, Set(2, 3, 4)) +
          SudokuCellKnown(1, 1, 1)

        val elementsAtR0C0 = grid.cells.filter { cell => cell.row == 0 && cell.column == 0 }
        assert(elementsAtR0C0.length === 1)
        assert(elementsAtR0C0.head.asInstanceOf[SudokuCellUnknown].values === Set(2, 3))

        val elementsAtR1C1 = grid.cells.filter { cell => cell.row == 1 && cell.column == 1 }
        assert(elementsAtR1C1.length === 1)
        assert(elementsAtR1C1.head.asInstanceOf[SudokuCellKnown].value === 1)
      }
    }
  }
}
