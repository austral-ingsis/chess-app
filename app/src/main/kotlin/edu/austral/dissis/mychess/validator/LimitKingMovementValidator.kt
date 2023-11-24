package edu.austral.dissis.mychess.validator

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import kotlin.math.abs

class LimitKingMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        return isValidForKing(movement.from, movement.to)
    }

    private fun isValidForKing(currentPosition: Position, finalPosition : Position) : Boolean{
        val difRow: Int = currentPosition.y - finalPosition.y
        val difCol: Int = currentPosition.x - finalPosition.x
        return abs(difRow) <= 1 && abs(difCol) <= 1
    }
}