package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.GameOver
import edu.austral.dissis.common.result.ValidatorResult

class WinCondition : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val opponentColor = if (movement.piece.color == PieceColor.BLACK) {PieceColor.WHITE} else {PieceColor.BLACK}
        val opponentPieces = board.getPiecesPositions().values.filter { it.color == opponentColor }
        if (opponentPieces.isEmpty()){
            return GameOver(opponentColor)
        }
        return FailureResult("sigue el juego")
    }
}