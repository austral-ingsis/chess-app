package edu.austral.dissis.mychess

import edu.austral.dissis.common.ManageTurns
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.turnStrategy.TurnStrategy

class ChessMovementStrategy : ManageTurns {
    override fun manageTurn(
        pieceToMove: Piece, currentBoard: Board, currentTurn: TurnStrategy, newBoard: Board
    ): TurnStrategy {
        return currentTurn.advanceTurn(pieceToMove.color)
    }
}