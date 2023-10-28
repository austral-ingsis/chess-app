package edu.austral.dissis.mychess.validator.commonValidators
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.result.FailureResult
import edu.austral.dissis.mychess.result.SuccessfulResult
import edu.austral.dissis.mychess.result.ValidatorResult
import edu.austral.dissis.mychess.validator.Movement
import edu.austral.dissis.mychess.validator.Validator

class DifferentColorValidator : Validator {

    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val targetPiece = board.getPiecesPositions()[movement.finalPosition]
        if (targetPiece != null) {
            if (targetPiece.color == movement.piece.color) {
                return FailureResult("Pieces have the same color")
            }
        }else return FailureResult("")
        return SuccessfulResult("Pieces are different color")
    }
}