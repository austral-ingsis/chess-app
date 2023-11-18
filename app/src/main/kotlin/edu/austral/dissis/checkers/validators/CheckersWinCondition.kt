package edu.austral.dissis.checkers.validators

import edu.austral.dissis.chess.gui.InvalidMove
import edu.austral.dissis.chess.gui.MoveResult
import edu.austral.dissis.chess.gui.PlayerColor
import edu.austral.dissis.common.WinCondition
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.GameOver
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult

class CheckersWinCondition : WinCondition {
    override fun validateMovement(board: Board, movement: Movement): MoveResult {
        val opponentColor = if (movement.piece.color == PieceColor.BLACK) {PieceColor.WHITE} else {PieceColor.BLACK}
        val opponentPieces = board.getPiecesPositions().values.filter { it.color == opponentColor }
        if (opponentPieces.isEmpty()){
            return edu.austral.dissis.chess.gui.GameOver(PlayerColor.WHITE)
        }
        return InvalidMove("Sigue el juego")
    }
}