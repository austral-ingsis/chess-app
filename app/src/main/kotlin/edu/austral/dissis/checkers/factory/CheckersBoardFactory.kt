package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.CheckersInitialPositions
import edu.austral.dissis.common.BoardFactory
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.ClassicBoard

class CheckersBoardFactory : BoardFactory{
    private val checkersFileName = "app/src/main/kotlin/edu/austral/dissis/checkers/resources/checkers_initial_positions.yml"

    override fun createInitialBoard(): Board {
        val boardSize = 8
        val pieceTypes = listOf(
            "pawn"
        )
        val piecesPositions = CheckersInitialPositions.readInitialPositions(checkersFileName, pieceTypes)
        val positions = fillPositions(boardSize, boardSize)
        return ClassicBoard(boardSize, boardSize, piecesPositions, positions)
    }
}