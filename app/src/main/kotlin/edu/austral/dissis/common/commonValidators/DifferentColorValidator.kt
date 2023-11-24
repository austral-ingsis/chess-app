package edu.austral.dissis.common.commonValidators
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Piece

class DifferentColorValidator : Validator {

    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val targetPiece = board.getPiecesPositions()[movement.to]
        val pieceToMove = board.getPiecesPositions()[movement.from]
        if (targetPiece != null) {
            if (isSameColor(targetPiece, pieceToMove)) {
                return false
            }
        }else return false
        return true
    }

    private fun isSameColor(targetPiece : Piece, pieceToMove: Piece?): Boolean{
        return targetPiece.color == pieceToMove?.color
    }
}