package edu.austral.dissis.common

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.ClassicBoard
import edu.austral.dissis.common.piece.Piece

interface BoardFactory {
    fun createInitialBoard() : Board
    fun createNewBoard(piecesPositions: Map<Position, Piece>, board: Board) : Board
    fun fillPositions(sizeX: Int, sizeY : Int): List<Position> {
        val positionsToReturn: MutableList<Position> = mutableListOf()
        for (i in 1..sizeX) {
            for (j in 1..sizeY) {
                positionsToReturn.add(Position(i, j))
            }
        }
        return positionsToReturn
    }
    companion object{
        fun createNewClassicBoard(piecesPositions: Map<Position, Piece>, board: Board): Board {
            return ClassicBoard(board.getSizeX(), board.getSizeY(), piecesPositions, board.getPositions())
        }
    }




}