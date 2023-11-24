package edu.austral.dissis.mychess.validator

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import kotlin.math.abs

class KnightMovementValidator: Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        // el caballo se mueve 1 en filas y 2 en columnas o viceversa.
        // come siempre
        return isValidForKnight(movement.from, movement.to)
    }

    private fun isValidForKnight(from: Position, to : Position) : Boolean{
        val difRow : Int = abs(from.y - to.y)
        val difCol : Int = abs(from.x - to.x)
        return (difRow == 1 && difCol == 2) || (difRow == 2 && difCol == 1)
    }
}