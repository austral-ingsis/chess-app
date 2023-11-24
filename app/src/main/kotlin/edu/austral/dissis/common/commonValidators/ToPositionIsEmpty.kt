package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.board.Board

class ToPositionIsEmpty : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val pieceTarget = board.getPiecesPositions()[movement.to]
        return pieceTarget == null
    }
}