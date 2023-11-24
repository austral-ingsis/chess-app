package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import kotlin.math.abs

class CheckerQueenMovementValidator  : Validator{
    override fun validateMovement(board: Board, movement: Movement): Boolean{
        val deltaX = movement.to.x - movement.from.x
        val deltaY = movement.to.y - movement.from.y
        val absDeltaX = abs(deltaX)
        val absDeltaY = abs(deltaY)
        return isValidMovement(absDeltaX, absDeltaY)
    }

    private fun isValidMovement(absDeltaX: Int, absDeltaY: Int): Boolean {
        return (absDeltaX == 0 && absDeltaY >= 1) ||      // Movimiento vertical
                (absDeltaX >= 1 && absDeltaY == 0) ||      // Movimiento horizontal
                (absDeltaX >= 1 && absDeltaY >= 1)
    }
}