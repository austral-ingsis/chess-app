package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import edu.austral.dissis.common.piece.PieceColor
import kotlin.math.abs

class RegularCheckerMovementValidator : Validator {

    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val pieceToMove = board.getPiece(movement.from)
        val deltaX = movement.to.x - movement.from.x
        val deltaY = movement.to.y - movement.from.y
        val absDeltaX = abs(deltaX)
        return isValidPawnMovement(absDeltaX, deltaY, pieceToMove!!.color)
    }

    private fun isValidPawnMovement(absDeltaX: Int, deltaY: Int, color: PieceColor): Boolean {
        return (absDeltaX == 0 && deltaY == 1 && color == PieceColor.BLACK) ||
                (absDeltaX == 1 && deltaY == 1 && color == PieceColor.BLACK) ||
                (absDeltaX == 0 && deltaY == -1 && color == PieceColor.WHITE) ||
                (absDeltaX == 1 && deltaY == -1 && color == PieceColor.WHITE)
    }
}