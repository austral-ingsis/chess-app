package edu.austral.dissis.mychess.validator.specificValidators

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.PieceColor
import edu.austral.dissis.mychess.validator.Movement

class KingInCheckValidator {
    // chequeo en todos los movimientos si mi rey esta en jaque, si es asi solo me puedo mover para
    // defenderlo sino es movimiento invalido. Si no puedo defenderlo mas es jaque mate entonces
    // game over.
    fun isKingInCheck(board: Board, kingPosition: Position, kingColor: PieceColor): Boolean {
        val opponentColor : PieceColor = if (kingColor == PieceColor.WHITE) PieceColor.BLACK else PieceColor.WHITE
        val opponentPieces = board.getPiecesPositions().values.filter { it.color == opponentColor }
        for (opponentPiece in opponentPieces){
            if (opponentPiece.validator.validateMovement(board, Movement(opponentPiece, kingPosition))::class.simpleName.equals("SuccessfulResult")){
                return true
            }
        }
        return false
    }
}