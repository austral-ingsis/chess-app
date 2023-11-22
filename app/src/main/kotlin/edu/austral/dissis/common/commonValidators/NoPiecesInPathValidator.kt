package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult
import kotlin.math.abs

class NoPiecesInPathValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition: Position = board.getPositionByPiece(movement.piece)
        val pieceName: String = movement.piece.id.takeWhile { it.isLetter() }

        return when (pieceName) {
            "bishop", "queen", "archbishop" -> validateBishopOrQueen(board, pieceActualPosition, movement.finalPosition)
            "rook", "queen", "chancellor" -> validateRookOrQueen(board, pieceActualPosition, movement.finalPosition)
            "pawn" -> validatePawn(board, pieceActualPosition, movement.piece.color)
            else -> FailureResult("Invalid piece type")
        }
    }

    private fun validateBishopOrQueen(board: Board, startPosition: Position, finalPosition: Position): ValidatorResult {
        val difRowBishop: Int = abs(startPosition.y - finalPosition.y)

        for (i in 1 until difRowBishop) {
            val path = calculatePathPosition(startPosition, finalPosition, i, difRowBishop)
            if (board.getPiecesPositions()[path] != null) {
                return FailureResult("There are a piece in path")
            }
        }

        return SuccessfulResult("Valid movement")
    }

    private fun validateRookOrQueen(board: Board, startPosition: Position, finalPosition: Position): ValidatorResult {
        val difRow: Int = (finalPosition.y - startPosition.y)
        val difCol: Int = (finalPosition.x - startPosition.x)
        val diferencia: Int = abs(difRow) + abs(difCol)

        for (i in 1 until diferencia) {
            val path = calculatePathPosition(startPosition, finalPosition, i, diferencia)
            if (board.getPiecesPositions()[path] != null) {
                return FailureResult("There are a piece in path")
            }
        }

        return SuccessfulResult("Valid movement")
    }

    private fun validatePawn(board: Board, startPosition: Position, color: PieceColor): ValidatorResult {
        val increment = if (color == PieceColor.WHITE) -1 else 1
        val path = Position(startPosition.x, startPosition.y + increment)

        return if (board.getPiecesPositions()[path] != null) {
            FailureResult("There are a piece in path")
        } else SuccessfulResult("Valid movement")
    }

    private fun calculatePathPosition(startPosition: Position, finalPosition: Position, step: Int, totalSteps: Int): Position {
        return Position(
            startPosition.x + step * ((finalPosition.x - startPosition.x) / totalSteps),
            startPosition.y + step * ((finalPosition.y - startPosition.y) / totalSteps)
        )
    }

}