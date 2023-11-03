package edu.austral.dissis.mychess.validator

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import kotlin.math.abs

class LimitKingMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition: Position = board.getPositionByPiece(movement.piece)
        val difRow: Int = pieceActualPosition.y - movement.finalPosition.y
        val difCol: Int = pieceActualPosition.x - movement.finalPosition.x
        return if(abs(difRow.toDouble()) <= 1 && abs(difCol.toDouble()) <= 1){
            SuccessfulResult("Success")
        }else FailureResult("Failed")
    }
}