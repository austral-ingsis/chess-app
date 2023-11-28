package edu.austral.dissis.mychess

import edu.austral.dissis.common.Game
import edu.austral.dissis.common.MovementStrategy
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.WinCondition
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.piece.PieceType
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.FinishGameResult
import edu.austral.dissis.common.result.Result
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.mychess.factory.ChessPieceFactory

class ChessWinCondition : WinCondition{
    // chequeo en todos los movimientos si mi rey esta en jaque, si es asi solo me puedo mover para
    // defenderlo sino es movimiento invalido. Si no puedo defenderlo mas es jaque mate entonces
    // game over.

    override fun validateWinCondition(board: Board, movement: Movement): Result {
        val currentPlayer = board.getPiece(movement.from)!!.color
        val opponentPlayer = if (currentPlayer == PieceColor.WHITE) PieceColor.BLACK else PieceColor.WHITE
        val kingPosition = findKingPosition(board, currentPlayer)

        val simulatedBoard = MovementStrategy(ChessPieceFactory()).moveTo(movement, board)

        if (isCheck(simulatedBoard, kingPosition, opponentPlayer)) {
            val legalMoves = getLegalMovesForKing(board, kingPosition, currentPlayer)
            return if (legalMoves.isNotEmpty()) {
                FailureResult("King is in check but has legal moves")
            } else {
                FinishGameResult(opponentPlayer)
            }
        }
        return SuccessfulResult(Game(board, currentPlayer, ChessWinCondition()))
    }

    private fun findKingPosition(board: Board, kingColor: PieceColor) : Position {
        for (piece in board.getPiecesPositions().values){
            val pieceType = piece.pieceType
            if (pieceType == PieceType.KING && piece.color == kingColor){
                return board.getPositionByPiece(piece)
            }
        }
        return Position(0, 0)
    }

    private fun isCheck(board: Board, kingPosition: Position, opponentPlayer: PieceColor): Boolean {
        for (row in 1 until board.getSizeX()) {
            for (col in 1 until board.getSizeY()) {
                val piece = board.getPiecesPositions()[Position(row, col)]
                if (piece != null && piece.color == opponentPlayer) {
                    val pieceMovement = Movement(Position(row, col), kingPosition)
                    if (piece.validator.validateMovement(board, pieceMovement)) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun getLegalMovesForKing(board: Board, kingPosition: Position, currentPlayer: PieceColor): List<Position> {
        val legalMoves = mutableListOf<Position>()
        for (row in -1..1) {
            for (col in -1..1) {
                val newPosition = Position(kingPosition.x + row, kingPosition.y + col)
                if (isValidMoveForKing(board, newPosition, kingPosition, currentPlayer)) {
                    legalMoves.add(newPosition)
                }
            }
        }
        return legalMoves
    }

    private fun isValidMoveForKing(board: Board, toPosition: Position, kingPosition: Position, currentPlayer: PieceColor): Boolean {
        if (!board.getPositions().contains(toPosition)) {
            return false
        }
        val opponentPlayer = if (currentPlayer == PieceColor.WHITE) PieceColor.BLACK else PieceColor.WHITE

        val simulatedBoard = MovementStrategy(ChessPieceFactory()).moveTo(Movement(kingPosition, toPosition), board)
        if (simulatedBoard == board){
            return false
        }

        return !isCheck(simulatedBoard, toPosition, opponentPlayer)
    }
}
