package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult
import kotlin.math.abs

class RegularCheckerMovementValidator : Validator {

    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition: Position = board.getPositionByPiece(movement.piece)
        val deltaX = movement.finalPosition.x - pieceActualPosition.x
        val deltaY = movement.finalPosition.y - pieceActualPosition.y
        val absDeltaX = abs(deltaX)

        if (isValidPawnMovement(absDeltaX, deltaY, movement.piece.color)) {
            return SuccessfulResult("Puede moverse a la posición adyacente vacía")
        }

        return FailureResult("Movimiento no válido")
    }

    private fun isValidPawnMovement(absDeltaX: Int, deltaY: Int, color: PieceColor): Boolean {
        return (absDeltaX == 0 && deltaY == 1 && color == PieceColor.BLACK) ||
                (absDeltaX == 1 && deltaY == 1 && color == PieceColor.BLACK) ||
                (absDeltaX == 0 && deltaY == -1 && color == PieceColor.WHITE) ||
                (absDeltaX == 1 && deltaY == -1 && color == PieceColor.WHITE)
    }




}