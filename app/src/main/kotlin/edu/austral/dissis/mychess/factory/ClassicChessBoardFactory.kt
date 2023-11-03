package edu.austral.dissis.mychess.factory

import edu.austral.dissis.common.BoardFactory
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.ClassicBoard
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.mychess.ChessInitialPositions

class ClassicChessBoardFactory : BoardFactory {

    private val classicFileName = "app/src/main/kotlin/edu/austral/dissis/mychess/resources/classic_initial_positions.yml"

    override fun createInitialBoard(): Board {
        val boardSize = 8
        val pieceTypes = listOf(
            "pawn", "rook", "knight", "bishop",
            "queen", "king"
        )
        val piecesPositions = ChessInitialPositions.readInitialPositions(classicFileName, pieceTypes)
        val positions = fillPositions(boardSize, boardSize)
        return ClassicBoard(boardSize, boardSize, piecesPositions, positions)
    }

    override fun createNewBoard(piecesPositions: Map<Position, Piece>, board: Board): Board {
        return ClassicBoard(board.getSizeX(), board.getSizeY(), piecesPositions, board.getPositions())
    }
}