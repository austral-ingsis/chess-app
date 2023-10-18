package edu.austral.dissis.mychess.moveResult

import Position
import exception.PositionNotFoundException
import piece.Piece
import piece.PieceColor

class ValidMovement(
    private val piecesPositions: Map<Position, Piece>,
    private val nextColor: PieceColor,
    private val positions: List<Position>
) : MoveResult {

    fun getPiecesPositions(): Map<Position, Piece> {
        return piecesPositions
    }

    fun getColor(): PieceColor {
        return nextColor
    }

    fun getPosition(position: Position): Position {
        for (i in positions) {
            if (i.x == position.x && i.y == position.y) {
                return i
            }
        }
        throw PositionNotFoundException("Position not found")
    }
}