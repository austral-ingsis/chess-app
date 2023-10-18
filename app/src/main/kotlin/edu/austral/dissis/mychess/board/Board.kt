package edu.austral.dissis.mychess.board

import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.Position

interface Board {
    fun getSizeX() : Int
    fun getSizeY() : Int
    fun getPiecesPositions() : Map<Position, Piece>
    fun isInBounds(position: Position) : Boolean
    fun getPositionByPiece(piece: Piece) : Position
    fun getPositions() : List<Position>
    fun getPieceByPosition(position: Position) : Piece
}