package edu.austral.dissis.mychess.factory

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.ReadYaml
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.ClassicBoard
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.mychess.ChessInitialPositions

class ChessBoardFactory {

    companion object {
        private val classicFileName = "app/src/main/kotlin/edu/austral/dissis/mychess/resources/classic_initial_positions.yml"
        private val capablancaFileName = "app/src/main/kotlin/edu/austral/dissis/mychess/resources/capablanca_initial_positions.yml"


        fun createInitialClassicChessBoard(): Board {
            val boardSize = 8
            val pieceTypes = listOf(
                "pawn", "rook", "knight", "bishop",
                "queen", "king"
            )
            val piecesPositions = ChessInitialPositions.readInitialPositions(classicFileName, pieceTypes)
            val positions = fillPositions(boardSize, boardSize)
            return ClassicBoard(boardSize, boardSize, piecesPositions, positions)
        }

        fun createInitialCapablancaBoard(): Board {
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

        private fun fillPositions(sizeX: Int, sizeY : Int): List<Position> {
            val positionsToReturn: MutableList<Position> = mutableListOf()
            for (i in 1..sizeX) {
                for (j in 1..sizeY) {
                    positionsToReturn.add(Position(i, j))
                }
            }
            return positionsToReturn
        }
    }

}
