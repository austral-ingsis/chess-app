package edu.austral.dissis.common.commonValidators
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult

class DifferentColorValidator : Validator {

    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val targetPiece = board.getPiecesPositions()[movement.finalPosition]
        if (targetPiece != null) {
            if (isSameColor(targetPiece, movement.piece)) {
                return FailureResult("Pieces have the same color")
            }
        }else return FailureResult("")
        return SuccessfulResult("Pieces are different color")
    }

    private fun isSameColor(targetPiece : Piece, pieceToMove: Piece): Boolean{
        return targetPiece.color == pieceToMove.color
    }
}