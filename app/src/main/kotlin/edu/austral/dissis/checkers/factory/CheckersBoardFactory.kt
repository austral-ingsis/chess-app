package edu.austral.dissis.checkers.factory

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.ClassicBoard
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.mychess.ReadYaml

class CheckersBoardFactory {

    companion object{
        private val checkersFileName = "app/src/main/kotlin/edu/austral/dissis/checkers/resources/checkers_initial_positions.yml"

        fun createInitialClassicCheckersBoard(): Board {
            val boardSize = 8
            val pieceTypes = listOf(
                "pawn"
            )
            val piecesPositions = ReadYaml.readInitialPositions(checkersFileName, pieceTypes)
            return createClassicBoard(boardSize, boardSize, piecesPositions)
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

        fun createNewClassicBoard(piecesPositions: Map<Position, Piece>, board: Board): Board {
            return ClassicBoard(board.getSizeX(), board.getSizeY(), piecesPositions, board.getPositions())
        }
    }

}