package edu.austral.dissis.checkers

import edu.austral.dissis.common.Game
import edu.austral.dissis.common.WinCondition
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.FinishGameResult
import edu.austral.dissis.common.result.Result
import edu.austral.dissis.common.result.SuccessfulResult

class CheckersWinCondition : WinCondition {
    override fun validateWinCondition(board: Board, movement: Movement): Result {
        val pieceToMove = board.getPiece(movement.from)!!
        val opponentColor = if (pieceToMove.color == PieceColor.BLACK) {PieceColor.WHITE} else {PieceColor.BLACK}
        val opponentPieces = board.getPiecesPositions().values.filter { it.color == opponentColor }
        if (opponentPieces.isEmpty()){
            return FinishGameResult(pieceToMove.color)
        }
        return SuccessfulResult(Game(board, pieceToMove.color, CheckersWinCondition()))
    }
}