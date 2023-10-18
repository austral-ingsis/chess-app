package validator

import Position
import board.Board

class VerticalMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        return (board.isInBounds(movement.finalPosition) && (pieceActualPosition.x == movement.finalPosition.x))
    }
}