package edu.austral.dissis.mychess.validator.specificValidators

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.piece.PieceColor
import edu.austral.dissis.mychess.result.FailureResult
import edu.austral.dissis.mychess.result.SuccessfulResult
import edu.austral.dissis.mychess.result.ValidatorResult
import edu.austral.dissis.mychess.validator.Movement
import edu.austral.dissis.mychess.validator.Validator

class PawnRegularMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceTarget : Piece? = board.getPiecesPositions()[movement.finalPosition]
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        val incrementByColor : Int = if (movement.piece.color == PieceColor.WHITE){ -1 } else 1
        if ((pieceActualPosition.y + incrementByColor == movement.finalPosition.y)){
            if (pieceTarget == null){
                return SuccessfulResult("Movimiento valido para el peon")
            }
        }
        return FailureResult("Invalid movement")
    }
}