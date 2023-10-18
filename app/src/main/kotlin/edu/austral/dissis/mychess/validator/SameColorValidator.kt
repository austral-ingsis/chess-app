package edu.austral.dissis.mychess.validator
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.Piece

class SameColorValidator : Validator {

    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val targetPiece: Piece? = board.getPiecesPositions()[movement.finalPosition]
        if (targetPiece != null) {
            if (targetPiece.getPieceColor() == movement.piece.getPieceColor()) {
                return true
            }
        }
        return false
    }
}