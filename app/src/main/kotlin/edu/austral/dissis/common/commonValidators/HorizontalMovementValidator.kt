package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board

class HorizontalMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        if (!board.isInBounds(movement.to)){
            return false
        }
        return isSameRow(movement.from, movement.to)
    }

    private fun isSameRow(currentPosition: Position, finalPosition : Position) : Boolean{
        return currentPosition.y == finalPosition.y
    }
}