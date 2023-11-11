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

        // Itera a través de todas las posiciones del tablero
        for (currentPosition in board.getPositions()){
            val currentPiece = board.getPiecesPositions()[currentPosition]

            // Verifica si la pieza en la posición actual es del mismo color que la pieza dada
            if (currentPiece != null && currentPiece.color == piece.color) {
                // Comprueba si hay movimientos de captura obligatorios desde esta posición
                val captureMoves = getCaptureMoves(board, currentPosition)

                if (captureMoves.isNotEmpty()) {
                    availableCaptureMoves.addAll(captureMoves)
                }
            }
        }

        // Si hay movimientos de captura obligatorios disponibles, devuelve true
        return availableCaptureMoves.isNotEmpty()
    }

    private fun getCaptureMoves(board: Board, startPosition: Position): List<Movement> {
        val mandatoryCaptureMoves = mutableListOf<Movement>()

        // Definir las direcciones en las que podrían ocurrir movimientos de captura obligatorios
        val directions = listOf(Pair(-1, -1), Pair(-1, 1), Pair(1, -1), Pair(1, 1))

        for ((deltaX, deltaY) in directions) {
            val targetPosition = Position(startPosition.x + 2 * deltaX, startPosition.y + 2 * deltaY)
            val middlePosition = Position(startPosition.x + deltaX, startPosition.y + deltaY)

            // Verificar si la posición objetivo está dentro del tablero
            if (board.getPositions().contains(targetPosition)){
                val currentPiece = board.getPiecesPositions()[startPosition]!!
                val middlePiece = board.getPiecesPositions()[middlePosition]
                val targetPiece = board.getPiecesPositions()[targetPosition]

                // Verificar si hay una pieza del oponente en la posición intermedia
                // y si la posición objetivo está vacía
                if (middlePiece != null && middlePiece.color != currentPiece.color && targetPiece == null) {
                    mandatoryCaptureMoves.add(Movement(currentPiece, targetPosition))
                }
            }
        }

        return mandatoryCaptureMoves
    }
}