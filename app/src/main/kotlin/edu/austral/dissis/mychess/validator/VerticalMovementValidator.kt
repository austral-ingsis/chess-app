package edu.austral.dissis.mychess.validator

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board

class VerticalMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        return (board.isInBounds(movement.finalPosition) && (pieceActualPosition.x == movement.finalPosition.x))
    }
}