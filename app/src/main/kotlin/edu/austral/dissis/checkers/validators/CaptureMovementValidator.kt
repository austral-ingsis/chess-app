package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult

class CaptureMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition: Position = board.getPositionByPiece(movement.piece)
        val deltaX = movement.finalPosition.x - pieceActualPosition.x
        val deltaY = movement.finalPosition.y - pieceActualPosition.y
        val middlePosition = Position(pieceActualPosition.x + deltaX / 2, pieceActualPosition.y + deltaY / 2)
        val middlePiece = board.getPiecesPositions()[middlePosition]
        if (!isValidCaptureMove(movement, middlePiece)) {
            return FailureResult("Movimiento de captura no válido")
        }
        return SuccessfulResult("Movimiento de captura exitoso")
    }

    private fun isValidCaptureMove(movement: Movement, middlePiece: Piece?): Boolean {
        return middlePiece != null && middlePiece.color != movement.piece.color
    }

}