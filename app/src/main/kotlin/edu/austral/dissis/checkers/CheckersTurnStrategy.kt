package edu.austral.dissis.checkers

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.turnStrategy.TurnStrategy

class CheckersTurnStrategy(private val color : PieceColor, private val board: Board, private val movement: Movement) : TurnStrategy {
    override fun advanceTurn(currentColor: PieceColor): TurnStrategy {
        // si puede comer no cambia el turno
        if (color == PieceColor.WHITE){
            if (board.getPiecesPositions().values.last().validator.validateMovement(board, movement) is SuccessfulResult){
                return CheckersTurnStrategy(PieceColor.WHITE, board, movement)
            }
            return CheckersTurnStrategy(PieceColor.BLACK, board, movement)
        }else {
            if (board.getPiecesPositions().values.last().validator.validateMovement(board, movement) is SuccessfulResult){
                return CheckersTurnStrategy(PieceColor.BLACK, board, movement)
            }
            return CheckersTurnStrategy(PieceColor.WHITE, board, movement)
        }
    }

    override fun getCurrentColor(): PieceColor {
        return color
    }
}