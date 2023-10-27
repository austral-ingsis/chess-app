package edu.austral.dissis.mychess.validator.specificValidators

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.PieceColor
import edu.austral.dissis.mychess.result.FailureResult
import edu.austral.dissis.mychess.result.SuccessfulResult
import edu.austral.dissis.mychess.result.ValidatorResult
import edu.austral.dissis.mychess.validator.Movement
import edu.austral.dissis.mychess.validator.Validator

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