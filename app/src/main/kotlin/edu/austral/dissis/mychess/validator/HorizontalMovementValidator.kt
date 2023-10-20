package edu.austral.dissis.mychess.validator

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.result.FailureResult
import edu.austral.dissis.mychess.result.SuccessfulResult
import edu.austral.dissis.mychess.result.ValidatorResult


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