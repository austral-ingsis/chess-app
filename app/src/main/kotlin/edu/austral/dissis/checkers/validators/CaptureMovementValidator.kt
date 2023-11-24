package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import edu.austral.dissis.common.piece.Piece

class CaptureMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val pieceToMove = board.getPiece(movement.from)
        val deltaX = movement.to.x - movement.from.x
        val deltaY = movement.to.y - movement.from.y
        val middlePosition = Position(movement.from.x + deltaX / 2, movement.from.y + deltaY / 2)
        val middlePiece = board.getPiecesPositions()[middlePosition]
        return canCapture(pieceToMove, middlePiece)
    }

    private fun canCapture(pieceToMove: Piece?, middlePiece: Piece?): Boolean {
        return middlePiece != null && middlePiece.color != pieceToMove?.color
    }

}