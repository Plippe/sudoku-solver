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

    describe("solved") {
      it("should fill an empty grid") {
        val grid = SudokuGrid(Seq()).solved
        assert(grid.cells.length === 81)
      }

      it("should remove a value from all cells in a row") {
        val cells = Seq(SudokuCellKnown(0, 0, 1))
        val grid = SudokuGrid(cells).solved

        for {
          r <- 0.to(8)
          genericCell <- grid.cells.find { cell => cell.row == r && cell.column == 0 }
          if genericCell.isInstanceOf[SudokuCellUnknown]
          cell = genericCell.asInstanceOf[SudokuCellUnknown]
        } {
          assert(!cell.values.contains(1))
        }
      }

      it("should remove a value from all cells in a column") {
        val cells = Seq(SudokuCellKnown(0, 0, 1))
        val grid = SudokuGrid(cells).solved

        for {
          c <- 0.to(8)
          genericCell <- grid.cells.find { cell => cell.row == 0 && cell.column == c }
          if genericCell.isInstanceOf[SudokuCellUnknown]
          cell = genericCell.asInstanceOf[SudokuCellUnknown]
        } {
          assert(!cell.values.contains(1))
        }
      }

      it("should remove a value from all cells in a square") {
        val cells = Seq(SudokuCellKnown(0, 0, 1))
        val grid = SudokuGrid(cells).solved

        for {
          r <- 0.to(2)
          c <- 0.to(2)
          genericCell <- grid.cells.find { cell => cell.row == r && cell.column == c }
          if genericCell.isInstanceOf[SudokuCellUnknown]
          cell = genericCell.asInstanceOf[SudokuCellUnknown]
        } {
          assert(!cell.values.contains(1), cell)
        }
      }

      it("should set a value if no cell can in a row") {
        val values = Set(1, 2, 3, 4, 5, 6, 7, 8)
        val base = for { r <- 0.to(8) } yield { SudokuCellUnknown(r, 0, values) }
        val cells = base.filterNot { c => c.row == 0 && c.column == 0 } :+ SudokuCellUnknown(0, 0)

        val grid = SudokuGrid(cells).solved
        val cell = grid.cells.find { cell => cell.row == 0 && cell.column == 0 }.get

        assert(grid.cells.length === 81)
        assert(cell.isInstanceOf[SudokuCellKnown])
        assert(cell.asInstanceOf[SudokuCellKnown].value === 9)
      }

      it("should set a value if no cell can in a column") {
        val values = Set(1, 2, 3, 4, 5, 6, 7, 8)
        val base = for { c <- 0.to(8) } yield { SudokuCellUnknown(0, c, values) }
        val cells = base.filterNot { c => c.row == 0 && c.column == 0 } :+ SudokuCellUnknown(0, 0)

        val grid = SudokuGrid(cells).solved
        val cell = grid.cells.find { cell => cell.row == 0 && cell.column == 0 }.get

        assert(grid.cells.length === 81)
        assert(cell.isInstanceOf[SudokuCellKnown])
        assert(cell.asInstanceOf[SudokuCellKnown].value === 9)
      }

      it("should set a value if no cell can in a square") {
        val values = Set(1, 2, 3, 4, 5, 6, 7, 8)
        val base = for { r <- 0.to(2); c <- 0.to(2) } yield { SudokuCellUnknown(r, c, values) }
        val cells = base.filterNot { c => c.row == 0 && c.column == 0 } :+ SudokuCellUnknown(0, 0)

        val grid = SudokuGrid(cells).solved
        val cell = grid.cells.find { cell => cell.row == 0 && cell.column == 0 }.get

        assert(grid.cells.length === 81)
        assert(cell.isInstanceOf[SudokuCellKnown])
        assert(cell.asInstanceOf[SudokuCellKnown].value === 9)
      }
    }
  }
}
