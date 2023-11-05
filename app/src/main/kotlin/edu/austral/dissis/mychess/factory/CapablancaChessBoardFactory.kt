package edu.austral.dissis.mychess.factory

import edu.austral.dissis.common.BoardFactory
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.ClassicBoard
import edu.austral.dissis.mychess.ChessInitialPositions

class CapablancaChessBoardFactory : BoardFactory {
    private val capablancaFileName = "app/src/main/kotlin/edu/austral/dissis/mychess/resources/capablanca_initial_positions.yml"

    override fun createInitialBoard(): Board {
        val boardSizeX = 10
        val boardSizeY = 8
        val pieceTypes = listOf(
            "pawn", "rook", "knight", "bishop",
            "queen", "king", "archbishop", "chancellor"
        )
        val piecesPositions = ChessInitialPositions.readInitialPositions(capablancaFileName, pieceTypes)
        val positions = fillPositions(boardSizeX, boardSizeY)
        return ClassicBoard(boardSizeX, boardSizeY, piecesPositions, positions)
    }
}