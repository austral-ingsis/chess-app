package validator
import board.Board
import piece.Piece

class SameColorValidator : Validator {

    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val targetPiece: Piece? = board.getPiecesPositions().get(movement.finalPosition)
        if (targetPiece != null) {
            if (targetPiece.getPieceColor() == movement.piece.getPieceColor()) {
                return true
            }
        }
        return false
    }
}