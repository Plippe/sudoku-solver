package com.github.plippe.sudokusolver.model

import org.scalatest.FunSpec

class TestSudokuCell extends FunSpec {
  describe("SudokuCell") {
    describe("apply") {
      it("should throw an error for an invalid row") {
        intercept[IllegalArgumentException] { SudokuCellKnown(-1, 0, 0) }
        intercept[IllegalArgumentException] { SudokuCellKnown(9, 0, 0) }
      }

      it("should throw an error for an invalid column") {
        intercept[IllegalArgumentException] { SudokuCellKnown(0, -1, 0) }
        intercept[IllegalArgumentException] { SudokuCellKnown(0, 9, 0) }
      }

      it("should return correct square") {
        assert(SudokuCellKnown(0, 0, 0).square === 0)
        assert(SudokuCellKnown(1, 0, 0).square === 0)
        assert(SudokuCellKnown(2, 0, 0).square === 0)
        assert(SudokuCellKnown(0, 1, 0).square === 0)
        assert(SudokuCellKnown(1, 1, 0).square === 0)
        assert(SudokuCellKnown(2, 1, 0).square === 0)
        assert(SudokuCellKnown(0, 2, 0).square === 0)
        assert(SudokuCellKnown(1, 2, 0).square === 0)
        assert(SudokuCellKnown(2, 2, 0).square === 0)

        assert(SudokuCellKnown(0, 3, 0).square === 1)
        assert(SudokuCellKnown(1, 3, 0).square === 1)
        assert(SudokuCellKnown(2, 3, 0).square === 1)
        assert(SudokuCellKnown(0, 4, 0).square === 1)
        assert(SudokuCellKnown(1, 4, 0).square === 1)
        assert(SudokuCellKnown(2, 4, 0).square === 1)
        assert(SudokuCellKnown(0, 5, 0).square === 1)
        assert(SudokuCellKnown(1, 5, 0).square === 1)
        assert(SudokuCellKnown(2, 5, 0).square === 1)

        assert(SudokuCellKnown(0, 6, 0).square === 2)
        assert(SudokuCellKnown(1, 6, 0).square === 2)
        assert(SudokuCellKnown(2, 6, 0).square === 2)
        assert(SudokuCellKnown(0, 7, 0).square === 2)
        assert(SudokuCellKnown(1, 7, 0).square === 2)
        assert(SudokuCellKnown(2, 7, 0).square === 2)
        assert(SudokuCellKnown(0, 8, 0).square === 2)
        assert(SudokuCellKnown(1, 8, 0).square === 2)
        assert(SudokuCellKnown(2, 8, 0).square === 2)

        assert(SudokuCellKnown(3, 0, 0).square === 3)
        assert(SudokuCellKnown(4, 0, 0).square === 3)
        assert(SudokuCellKnown(5, 0, 0).square === 3)
        assert(SudokuCellKnown(3, 1, 0).square === 3)
        assert(SudokuCellKnown(4, 1, 0).square === 3)
        assert(SudokuCellKnown(5, 1, 0).square === 3)
        assert(SudokuCellKnown(3, 2, 0).square === 3)
        assert(SudokuCellKnown(4, 2, 0).square === 3)
        assert(SudokuCellKnown(5, 2, 0).square === 3)

        assert(SudokuCellKnown(3, 3, 0).square === 4)
        assert(SudokuCellKnown(4, 3, 0).square === 4)
        assert(SudokuCellKnown(5, 3, 0).square === 4)
        assert(SudokuCellKnown(3, 4, 0).square === 4)
        assert(SudokuCellKnown(4, 4, 0).square === 4)
        assert(SudokuCellKnown(5, 4, 0).square === 4)
        assert(SudokuCellKnown(3, 5, 0).square === 4)
        assert(SudokuCellKnown(4, 5, 0).square === 4)
        assert(SudokuCellKnown(5, 5, 0).square === 4)

        assert(SudokuCellKnown(3, 6, 0).square === 5)
        assert(SudokuCellKnown(4, 6, 0).square === 5)
        assert(SudokuCellKnown(5, 6, 0).square === 5)
        assert(SudokuCellKnown(3, 7, 0).square === 5)
        assert(SudokuCellKnown(4, 7, 0).square === 5)
        assert(SudokuCellKnown(5, 7, 0).square === 5)
        assert(SudokuCellKnown(3, 8, 0).square === 5)
        assert(SudokuCellKnown(4, 8, 0).square === 5)
        assert(SudokuCellKnown(5, 8, 0).square === 5)

        assert(SudokuCellKnown(6, 0, 0).square === 6)
        assert(SudokuCellKnown(7, 0, 0).square === 6)
        assert(SudokuCellKnown(8, 0, 0).square === 6)
        assert(SudokuCellKnown(6, 1, 0).square === 6)
        assert(SudokuCellKnown(7, 1, 0).square === 6)
        assert(SudokuCellKnown(8, 1, 0).square === 6)
        assert(SudokuCellKnown(6, 2, 0).square === 6)
        assert(SudokuCellKnown(7, 2, 0).square === 6)
        assert(SudokuCellKnown(8, 2, 0).square === 6)

        assert(SudokuCellKnown(6, 3, 0).square === 7)
        assert(SudokuCellKnown(7, 3, 0).square === 7)
        assert(SudokuCellKnown(8, 3, 0).square === 7)
        assert(SudokuCellKnown(6, 4, 0).square === 7)
        assert(SudokuCellKnown(7, 4, 0).square === 7)
        assert(SudokuCellKnown(8, 4, 0).square === 7)
        assert(SudokuCellKnown(6, 5, 0).square === 7)
        assert(SudokuCellKnown(7, 5, 0).square === 7)
        assert(SudokuCellKnown(8, 5, 0).square === 7)

        assert(SudokuCellKnown(6, 6, 0).square === 8)
        assert(SudokuCellKnown(7, 6, 0).square === 8)
        assert(SudokuCellKnown(8, 6, 0).square === 8)
        assert(SudokuCellKnown(6, 7, 0).square === 8)
        assert(SudokuCellKnown(7, 7, 0).square === 8)
        assert(SudokuCellKnown(8, 7, 0).square === 8)
        assert(SudokuCellKnown(6, 8, 0).square === 8)
        assert(SudokuCellKnown(7, 8, 0).square === 8)
        assert(SudokuCellKnown(8, 8, 0).square === 8)
      }

      it("should have 1 to 9 possible values for unknown cells") {
        val unknow = SudokuCellUnknown(0, 0)
        assert(unknow.values.size === 9)
        assert(unknow.values.contains(1))
        assert(unknow.values.contains(2))
        assert(unknow.values.contains(3))
        assert(unknow.values.contains(4))
        assert(unknow.values.contains(5))
        assert(unknow.values.contains(6))
        assert(unknow.values.contains(7))
        assert(unknow.values.contains(8))
        assert(unknow.values.contains(9))
      }
    }
  }
}
