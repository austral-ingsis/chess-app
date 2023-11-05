package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult

class CaptureMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition: Position = board.getPositionByPiece(movement.piece)
        val deltaX = movement.finalPosition.x - pieceActualPosition.x
        val deltaY = movement.finalPosition.y - pieceActualPosition.y
        val piecesPositionsCopy = board.getPiecesPositions().toMutableMap()

        // Verifica si se está realizando una captura
        val middlePosition = Position(pieceActualPosition.x + deltaX / 2, pieceActualPosition.y + deltaY / 2)
        val middlePiece = board.getPiecesPositions()[middlePosition]

        if (middlePiece != null && middlePiece.color != movement.piece.color) {

            piecesPositionsCopy.remove(middlePosition)
//            piecesPositionsCopy.remove(movement.finalPosition)
            return SuccessfulResult("Movimiento de captura exitoso")
        }

        return FailureResult("Movimiento no válido")
    }
}