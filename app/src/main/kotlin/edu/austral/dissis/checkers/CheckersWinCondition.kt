package edu.austral.dissis.checkers

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.Adapter
import edu.austral.dissis.common.WinCondition
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.piece.PieceColor

class CheckersWinCondition : WinCondition {
    override fun validateMovement(board: Board, movement: Movement): MoveResult {
        val opponentColor = if (movement.piece.color == PieceColor.BLACK) {PieceColor.WHITE} else {PieceColor.BLACK}
        val opponentPieces = board.getPiecesPositions().values.filter { it.color == opponentColor }
        if (opponentPieces.isEmpty()){
            return GameOver(Adapter().adaptPieceColorToPlayerColor(movement.piece.color))
        }
        return NewGameState(Adapter().adaptPiecesToChessPieces(board, board.getPiecesPositions().values.toList()), Adapter().adaptPieceColorToPlayerColor(movement.piece.color))
    }
}