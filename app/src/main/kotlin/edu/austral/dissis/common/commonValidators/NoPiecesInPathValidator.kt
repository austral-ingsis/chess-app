package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.piece.PieceType
import kotlin.math.abs

class NoPiecesInPathValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val pieceToMove = board.getPiece(movement.from)
        val pieceType  = pieceToMove?.pieceType

        return when (pieceType) {
            PieceType.BISHOP, PieceType.QUEEN, PieceType.ARCHBISHOP -> validateBishopOrQueen(board, movement.from, movement.to)
            PieceType.ROOK, PieceType.QUEEN, PieceType.CHANCELLOR -> validateRookOrQueen(board, movement.from, movement.to)
            PieceType.PAWN -> validatePawn(board, movement.from, pieceToMove.color)
            else -> false
        }
    }

    private fun validateBishopOrQueen(board: Board, startPosition: Position, finalPosition: Position): Boolean {
        val difRowBishop: Int = abs(startPosition.y - finalPosition.y)

        for (i in 1 until difRowBishop) {
            val path = calculatePathPosition(startPosition, finalPosition, i, difRowBishop)
            if (board.getPiecesPositions()[path] != null) {
                return false
            }
        }

        return true
    }

    private fun validateRookOrQueen(board: Board, startPosition: Position, finalPosition: Position): Boolean {
        val difRow: Int = (finalPosition.y - startPosition.y)
        val difCol: Int = (finalPosition.x - startPosition.x)
        val diferencia: Int = abs(difRow) + abs(difCol)

        for (i in 1 until diferencia) {
            val path = calculatePathPosition(startPosition, finalPosition, i, diferencia)
            if (board.getPiecesPositions()[path] != null) {
                return false
            }
        }

        return true
    }

    private fun validatePawn(board: Board, startPosition: Position, color: PieceColor): Boolean {
        val increment = if (color == PieceColor.WHITE) -1 else 1
        val path = Position(startPosition.x, startPosition.y + increment)

        return board.getPiecesPositions()[path] == null
    }

    private fun calculatePathPosition(startPosition: Position, finalPosition: Position, step: Int, totalSteps: Int): Position {
        return Position(
            startPosition.x + step * ((finalPosition.x - startPosition.x) / totalSteps),
            startPosition.y + step * ((finalPosition.y - startPosition.y) / totalSteps)
        )
    }

}