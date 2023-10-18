package edu.austral.dissis.mychess.validator

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import kotlin.math.abs

class DiagonalMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement) : Boolean {
        if (!board.isInBounds(movement.finalPosition)) {
            return false
        }
        // si las diferencias entre filas y columnas son iguales ⇒ es diagonal
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        val difRow : Int = abs(pieceActualPosition.y - movement.finalPosition.y)
        val difCol : Int = abs(pieceActualPosition.x - movement.finalPosition.x)
        return difRow == difCol
    }
}