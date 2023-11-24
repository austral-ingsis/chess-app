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

class PawnInitialMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val pieceTarget : Piece? = board.getPiecesPositions()[movement.to]
        val pieceToMove = board.getPiece(movement.from)!!
        val incrementByColor : Int = if (pieceToMove.color == PieceColor.WHITE){ -1 } else 1
        val boardLimit : Int = if (pieceToMove.color == PieceColor.WHITE) board.getSizeY() -1 else (board.getSizeY() - (board.getSizeY() - 2))
        if (pawnIsInInitialPosition(movement.from, boardLimit, incrementByColor, movement.to)){
            if (pieceTarget == null){
                return true
            }
        }
        return false
    }

    private fun pawnIsInInitialPosition(currentPosition: Position, boardLimit: Int, incrementByColor: Int, finalPosition: Position): Boolean{
        return (currentPosition.y == boardLimit) && (finalPosition.y == boardLimit + 2 * incrementByColor)
    }
}