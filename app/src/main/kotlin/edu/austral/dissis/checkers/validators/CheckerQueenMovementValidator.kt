package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult
import kotlin.math.abs

class CheckerQueenMovementValidator  : Validator{
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition: Position = board.getPositionByPiece(movement.piece)
        val deltaX = movement.finalPosition.x - pieceActualPosition.x
        val deltaY = movement.finalPosition.y - pieceActualPosition.y
        val absDeltaX = abs(deltaX)
        val absDeltaY = abs(deltaY)

        if (isValidMovement(absDeltaX, absDeltaY)) {
            return SuccessfulResult("Puede moverse a la posición adyacente vacía")
        }

        return FailureResult("Movimiento no válido")
    }

    private fun isValidMovement(absDeltaX: Int, absDeltaY: Int): Boolean {
        return (absDeltaX == 0 && absDeltaY >= 1) ||      // Movimiento vertical
                (absDeltaX >= 1 && absDeltaY == 0) ||      // Movimiento horizontal
                (absDeltaX >= 1 && absDeltaY >= 1)
    }
}