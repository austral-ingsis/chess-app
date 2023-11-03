package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult

class ToPositionIsEmpty : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val toPosition = movement.finalPosition
        val pieceTarget = board.getPiecesPositions()[toPosition]
        return if (pieceTarget == null){
            SuccessfulResult("Empty Position")
        }else FailureResult("No empty position")
    }
}