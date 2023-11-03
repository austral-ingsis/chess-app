package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult


class HorizontalMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        if (!board.isInBounds(movement.finalPosition)){
            return FailureResult("Out of bounds")
        }
        if (pieceActualPosition.y == movement.finalPosition.y){
            return SuccessfulResult("Horizontal movement")
        } else return FailureResult("It's not horizontal movement")
    }
}