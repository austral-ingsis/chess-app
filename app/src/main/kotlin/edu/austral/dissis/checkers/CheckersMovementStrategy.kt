package edu.austral.dissis.checkers

import edu.austral.dissis.common.ManageTurns
import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.turnStrategy.TurnStrategy

class CheckersMovementStrategy : ManageTurns {
    override fun manageTurn(pieceToMove: Piece, currentBoard: Board, currentTurn: TurnStrategy, newBoard: Board): TurnStrategy {
        return if (!hasCaptureMoves(currentBoard, pieceToMove)){
            currentTurn.advanceTurn(pieceToMove.color)
        }else if (!hasCaptureMoves(newBoard, pieceToMove)){
            currentTurn.advanceTurn(pieceToMove.color)
        }else currentTurn
    }

    private fun hasCaptureMoves(board: Board, piece: Piece): Boolean {
        val availableCaptureMoves = mutableListOf<Movement>()

        for (currentPosition in board.getPositions()){
            val currentPiece = board.getPiecesPositions()[currentPosition]

            if (currentPiece != null && currentPiece.color == piece.color) {
                val captureMoves = getCaptureMoves(board, currentPosition)
                if (captureMoves.isNotEmpty()) {
                    availableCaptureMoves.addAll(captureMoves)
                }
            }
        }

        return availableCaptureMoves.isNotEmpty()
    }

    private fun getCaptureMoves(board: Board, startPosition: Position): List<Movement> {
        val mandatoryCaptureMoves = mutableListOf<Movement>()

        val directions = listOf(Position(-1, -1), Position(-1, 1), Position(1, -1), Position(1, 1))

        for ((deltaX, deltaY) in directions) {
            val targetPosition = Position(startPosition.x + 2 * deltaX, startPosition.y + 2 * deltaY)
            val middlePosition = Position(startPosition.x + deltaX, startPosition.y + deltaY)

            if (boardContainsTargetPosition(board, targetPosition)){
                val currentPiece = board.getPiecesPositions()[startPosition]!!
                val middlePiece = board.getPiecesPositions()[middlePosition]
                val targetPiece = board.getPiecesPositions()[targetPosition]

                if (middlePiece != null && middlePiece.color != currentPiece.color && targetPiece == null) {
                    mandatoryCaptureMoves.add(Movement(startPosition, targetPosition))
                }
            }
        }

        return mandatoryCaptureMoves
    }

    private fun boardContainsTargetPosition(board: Board, targetPosition: Position): Boolean{
        return board.getPositions().contains(targetPosition)
    }
}