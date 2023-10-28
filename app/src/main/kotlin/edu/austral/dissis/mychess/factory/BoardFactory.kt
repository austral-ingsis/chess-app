package edu.austral.dissis.mychess.factory

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.ReadYaml
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.board.ClassicBoard
import edu.austral.dissis.mychess.piece.Piece

class BoardFactory {

    companion object {
        private val classicFileName = "app/src/main/kotlin/edu/austral/dissis/mychess/resources/classic_initial_positions.yml"
        private val capablancaFileName = "app/src/main/kotlin/edu/austral/dissis/mychess/resources/capablanca_initial_positions.yml"

        fun createNewClassicBoard(piecesPositions: Map<Position, Piece>, board: Board): Board {
            return ClassicBoard(board.getSizeX(), board.getSizeY(), piecesPositions, board.getPositions())
        }

        fun createInitialClassicBoard(): Board {
            val boardSize = 8
            val pieceTypes = listOf(
                "pawn", "rook", "knight", "bishop",
                "queen", "king"
            )
            val piecesPositions = ReadYaml.readInitialPositions(classicFileName, pieceTypes)
            return createClassicBoard(boardSize, boardSize, piecesPositions)
        }

        fun createInitialCapablancaBoard(): Board {
            val boardSizeX = 10
            val boardSizeY = 8
            val pieceTypes = listOf(
                "pawn", "rook", "knight", "bishop",
                "queen", "king", "archbishop", "chancellor"
            )
            val piecesPositions = ReadYaml.readInitialPositions(capablancaFileName, pieceTypes)
            return createClassicBoard(boardSizeX, boardSizeY, piecesPositions)
        }

        private fun createClassicBoard(boardSizeX: Int, boardSizeY: Int, piecesPositions: Map<Position, Piece>): Board {
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
