package edu.austral.dissis.common

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.turnStrategy.TurnStrategy

interface ManageTurns {
    fun manageTurn(pieceToMove: Piece, currentBoard: Board, currentTurn: TurnStrategy, newBoard: Board): TurnStrategy

}