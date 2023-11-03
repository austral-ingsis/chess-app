package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult

class VerticalMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        return if (board.isInBounds(movement.finalPosition) && (pieceActualPosition.x == movement.finalPosition.x)){
            SuccessfulResult("Vertical movement")
        }else FailureResult("It's not vertical movement")
    }
}