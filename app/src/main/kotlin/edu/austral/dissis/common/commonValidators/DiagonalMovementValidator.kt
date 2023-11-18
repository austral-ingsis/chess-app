package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult
import kotlin.math.abs

class DiagonalMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement) : ValidatorResult {
        if (!board.isInBounds(movement.finalPosition)) {
            return FailureResult("Out of bounds")
        }
        // si las diferencias entre filas y columnas son iguales ⇒ es diagonal
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        return if (isDiagonal(pieceActualPosition, movement)){
            SuccessfulResult("Diagonal Movement")
        }else FailureResult("It's not diagonal movement")
    }

    private fun isDiagonal(position: Position, movement: Movement) : Boolean{
        val difRow : Int = abs(position.y - movement.finalPosition.y)
        val difCol : Int = abs(position.x - movement.finalPosition.x)
        return difRow == difCol
    }
}