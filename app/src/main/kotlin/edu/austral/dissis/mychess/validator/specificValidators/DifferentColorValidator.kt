package edu.austral.dissis.mychess.validator.specificValidators
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.result.FailureResult
import edu.austral.dissis.mychess.result.SuccessfulResult
import edu.austral.dissis.mychess.result.ValidatorResult
import edu.austral.dissis.mychess.validator.Movement
import edu.austral.dissis.mychess.validator.Validator

class DifferentColorValidator : Validator {

    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val targetPiece: Piece? = board.getPiecesPositions()[movement.finalPosition]
        if (targetPiece != null) {
            if (targetPiece.color == movement.piece.color) {
                return FailureResult("Pieces have the same color")
            }
        }
        return FailureResult("Pieces are different color")
    }
}