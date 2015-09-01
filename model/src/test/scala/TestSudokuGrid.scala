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
  }
}
