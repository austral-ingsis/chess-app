package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import kotlin.math.abs

class DiagonalMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement) : Boolean {
        if (!board.isInBounds(movement.to)) {
            return false
        }
        // si las diferencias entre filas y columnas son iguales ⇒ es diagonal
        return isDiagonal(movement.from, movement)
    }

    private fun isDiagonal(position: Position, movement: Movement) : Boolean{
        val difRow : Int = abs(position.y - movement.to.y)
        val difCol : Int = abs(position.x - movement.to.x)
        return difRow == difCol
    }
}