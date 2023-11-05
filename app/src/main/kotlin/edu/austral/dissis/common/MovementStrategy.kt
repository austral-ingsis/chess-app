package edu.austral.dissis.common

import edu.austral.dissis.checkers.factory.CheckersPieceFactory
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.commonValidators.PromotionValidator
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.mychess.factory.ChessPieceFactory

class MovementStrategy {

    fun moveTo(pieceToMove: Piece, toPosition: Position, board: Board): Board {
        val piecesPositionsCopy: MutableMap<Position, Piece> = board.getPiecesPositions().toMutableMap()
        val fromPosition: Position = board.getPositionByPiece(pieceToMove)
        val pieceActualPosition: Position = board.getPositionByPiece(pieceToMove)
        val deltaX = toPosition.x - pieceActualPosition.x
        val deltaY = toPosition.y - pieceActualPosition.y
        val middlePosition = Position(pieceActualPosition.x + deltaX / 2, pieceActualPosition.y + deltaY / 2)

        if (pieceToMove.validator.validateMovement(board, Movement(pieceToMove, toPosition)) is SuccessfulResult) {
            val targetPiece: Piece? = board.getPiecesPositions()[toPosition]
            val middlePiece = board.getPiecesPositions()[middlePosition]

            if (targetPiece == null) {
                // Mueve la pieza a la posición de destino y elimina la original
                piecesPositionsCopy.remove(fromPosition)
                if (middlePiece != null && (pieceToMove.id.takeWhile { it.isLetter() } == "pawn" || pieceToMove.id.takeWhile { it.isLetter() } == "queen")){
                    // Captura la pieza enemiga
                    piecesPositionsCopy.remove(middlePosition)
                }
                if (toPosition.y == 1 || toPosition.y == 8){
                    val id = pieceToMove.id.filter { it.isDigit() }
                    piecesPositionsCopy[toPosition] = CheckersPieceFactory.createQueen( pieceToMove.color, id.toInt())
                }else{
                    piecesPositionsCopy[toPosition] = pieceToMove
                }
            } else if (targetPiece.color != pieceToMove.color) {
                // Come la pieza enemiga
                piecesPositionsCopy.remove(fromPosition)
                piecesPositionsCopy.remove(toPosition)
                piecesPositionsCopy[toPosition] = pieceToMove
            }


            return BoardFactory.createNewClassicBoard(piecesPositionsCopy, board)
        }
        return board
    }
}