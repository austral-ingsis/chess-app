package edu.austral.dissis.mychess.validator

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator

class PawnRegularMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceTarget : Piece? = board.getPiecesPositions()[movement.finalPosition]
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        val incrementByColor : Int = if (movement.piece.color == PieceColor.WHITE){ -1 } else 1
        if (isRegularMove(pieceActualPosition, incrementByColor, movement.finalPosition)){
            if (pieceTarget == null){
                return SuccessfulResult("Movimiento valido para el peon")
            }
        }
        return FailureResult("Invalid movement")
    }

    private fun isRegularMove(currentPosition: Position, incrementByColor: Int, finalPosition: Position) : Boolean{
        return (currentPosition.y + incrementByColor == finalPosition.y)
    }
}