package edu.austral.dissis.common.board

import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.Position

interface Board {
    fun getSizeX() : Int
    fun getSizeY() : Int
    fun getPiecesPositions() : Map<Position, Piece>
    fun isInBounds(position: Position) : Boolean
    fun getPositionByPiece(piece: Piece) : Position
    fun getPositions() : List<Position>
    fun getPiece(position: Position): Piece?
}