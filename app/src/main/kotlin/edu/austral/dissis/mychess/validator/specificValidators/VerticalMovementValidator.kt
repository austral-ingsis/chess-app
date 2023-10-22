package edu.austral.dissis.mychess.validator.specificValidators

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.result.FailureResult
import edu.austral.dissis.mychess.result.SuccessfulResult
import edu.austral.dissis.mychess.result.ValidatorResult
import edu.austral.dissis.mychess.validator.Movement
import edu.austral.dissis.mychess.validator.Validator

class VerticalMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        return if (board.isInBounds(movement.finalPosition) && (pieceActualPosition.x == movement.finalPosition.x)){
            SuccessfulResult("Vertical movement")
        }else FailureResult("It's not vertical movement")
    }
}