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

class PawnInitialMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceTarget : Piece? = board.getPiecesPositions()[movement.finalPosition]
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        val incrementByColor : Int = if (movement.piece.color == PieceColor.WHITE){ -1 } else 1
        val boardLimit : Int = if (movement.piece.color == PieceColor.WHITE) board.getSizeY() -1 else (board.getSizeY() - (board.getSizeY() - 2))
        if ((pieceActualPosition.y == boardLimit) && (movement.finalPosition.y == boardLimit + 2 * incrementByColor)){
            if (pieceTarget == null){
                return SuccessfulResult("Movimiento inicial del peon")
            }
        }
        return FailureResult("")
    }
}