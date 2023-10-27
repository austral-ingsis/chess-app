package edu.austral.dissis.mychess.validator.specificValidators

import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.result.FailureResult
import edu.austral.dissis.mychess.result.SuccessfulResult
import edu.austral.dissis.mychess.result.ValidatorResult
import edu.austral.dissis.mychess.validator.Movement
import edu.austral.dissis.mychess.validator.Validator

class ToPositionIsEmpty : Validator{
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val toPosition = movement.finalPosition
        val pieceTarget = board.getPiecesPositions()[toPosition]
        return if (pieceTarget == null){
            SuccessfulResult("Empty Position")
        }else FailureResult("No empty position")
    }
}