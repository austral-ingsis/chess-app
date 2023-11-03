package edu.austral.dissis.mychess.validator

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult
import java.lang.RuntimeException

class KingInCheckValidator : Validator{
    // chequeo en todos los movimientos si mi rey esta en jaque, si es asi solo me puedo mover para
    // defenderlo sino es movimiento invalido. Si no puedo defenderlo mas es jaque mate entonces
    // game over.

    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val kingColor = movement.piece.color
        val kingPosition = findKingPosition(board, kingColor)
        val opponentColor = if (kingColor == PieceColor.WHITE) PieceColor.BLACK else PieceColor.WHITE
        val opponentPieces = board.getPiecesPositions().values.filter { it.color == opponentColor }
        if (isKingInCheck(kingPosition, opponentPieces, board)) {
            return FailureResult("King is in check")
        }
        return SuccessfulResult("King's safe")
    }

    private fun isKingInCheck(kingPosition: Position, opponentPieces: List<Piece>, board: Board): Boolean {
        for (opponentPiece in opponentPieces) {
            // Use the opponentPiece's validator to check if it can attack the king's position.
            val movement = Movement(opponentPiece, kingPosition)
            val result = opponentPiece.validator.validateMovement(board, movement)
            if (result is SuccessfulResult) {
                return true // King is in check
            }
        }
        return false // King's safe
    }

    private fun findKingPosition(board: Board, kingColor: PieceColor) : Position {
        for (piece in board.getPiecesPositions().values){
            val pieceName : String = piece.id.takeWhile { it.isLetter() }
            if (pieceName == "king" && piece.color == kingColor){
                return board.getPositionByPiece(piece)
            }
        }
        throw RuntimeException("")
    }
}