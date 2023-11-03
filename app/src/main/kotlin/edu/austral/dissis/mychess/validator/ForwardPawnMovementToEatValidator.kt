package edu.austral.dissis.mychess.validator

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class ForwardPawnMovementToEatValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition: Position = board.getPositionByPiece(movement.piece)
        val increment = if (movement.piece.color == PieceColor.WHITE) -1 else 1
        if (movement.finalPosition.y === (pieceActualPosition.y + increment)) {
            return SuccessfulResult("can move")
        }
        return FailureResult("can't move")
    }
}