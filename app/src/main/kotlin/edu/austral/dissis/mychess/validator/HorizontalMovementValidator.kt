package validator

import Position
import board.Board


class HorizontalMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        if (!board.isInBounds(movement.finalPosition)){
            return false
        }
        return pieceActualPosition.y == movement.finalPosition.y
    }
}