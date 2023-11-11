package edu.austral.dissis.checkers.validators

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.Validator
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult

class CaptureMovementValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition: Position = board.getPositionByPiece(movement.piece)
        val deltaX = movement.finalPosition.x - pieceActualPosition.x
        val deltaY = movement.finalPosition.y - pieceActualPosition.y

        val middlePosition = Position(pieceActualPosition.x + deltaX / 2, pieceActualPosition.y + deltaY / 2)
        val middlePiece = board.getPiecesPositions()[middlePosition]

        if (!isValidCaptureMove(movement, middlePiece)) {
            return FailureResult("Movimiento de captura no válido")
        }

        // Verificar si hay más movimientos de captura obligatorios
//        if (hasCaptureMoves(board, movement.piece)) {
//            return SuccessfulResult("Movimiento de captura exitoso. Debe realizar otra captura.")
//        }

        return SuccessfulResult("Movimiento de captura exitoso")
    }

    fun isValidCaptureMove(movement: Movement, middlePiece: Piece?): Boolean {
        // Verifica si hay una pieza en la posición intermedia
        return middlePiece != null && middlePiece.color != movement.piece.color
    }

//    fun hasCaptureMoves(board: Board, piece: Piece): Boolean {
//        val availableCaptureMoves = mutableListOf<Movement>()
//
//        // Itera a través de todas las posiciones del tablero
//        for (x in 0 until board.getSizeX()) {
//            for (y in 0 until board.getSizeY()) {
//                val currentPosition = Position(x, y)
//                val currentPiece = board.getPiecesPositions()[currentPosition]
//
//                // Verifica si la pieza en la posición actual es del mismo color que la pieza dada
//                if (currentPiece != null && currentPiece.color == piece.color) {
//                    // Comprueba si hay movimientos de captura obligatorios desde esta posición
//                    val captureMoves = getCaptureMoves(board, currentPosition)
//
//                    if (captureMoves.isNotEmpty()) {
//                        availableCaptureMoves.addAll(captureMoves)
//                    }
//                }
//            }
//        }
//
//        // Si hay movimientos de captura obligatorios disponibles, devuelve true
//        return availableCaptureMoves.isNotEmpty()
//    }
//
//    fun getCaptureMoves(board: Board, startPosition: Position): List<Movement> {
//        val mandatoryCaptureMoves = mutableListOf<Movement>()
//
//        // Definir las direcciones en las que podrían ocurrir movimientos de captura obligatorios
//        val directions = listOf(Pair(-1, -1), Pair(-1, 1), Pair(1, -1), Pair(1, 1))
//
//        for ((deltaX, deltaY) in directions) {
//            val targetPosition = Position(startPosition.x + 2 * deltaX, startPosition.y + 2 * deltaY)
//            val middlePosition = Position(startPosition.x + deltaX, startPosition.y + deltaY)
//
//            // Verificar si la posición objetivo está dentro del tablero
//            if (board.getPositions().contains(targetPosition)){
//                val currentPiece = board.getPiecesPositions()[startPosition]!!
//                val middlePiece = board.getPiecesPositions()[middlePosition]
//                val targetPiece = board.getPiecesPositions()[targetPosition]
//
//                // Verificar si hay una pieza del oponente en la posición intermedia
//                // y si la posición objetivo está vacía
//                if (middlePiece != null && middlePiece.color != currentPiece.color && targetPiece == null) {
//                    mandatoryCaptureMoves.add(Movement(currentPiece, targetPosition))
//                }
//            }
//        }
//
//        return mandatoryCaptureMoves
//    }


}