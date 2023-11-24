package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.board.Board

class VerticalMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        return board.isInBounds(movement.to) && (movement.from.x == movement.to.x)
    }
}