package edu.austral.dissis.mychess.validator

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import edu.austral.dissis.common.piece.PieceColor

class ForwardPawnMovementToEatValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val pieceToMove = board.getPiece(movement.from)
        val increment = if (pieceToMove!!.color == PieceColor.WHITE) -1 else 1
        return movement.to.y == (movement.from.y + increment)
    }
}