package edu.austral.dissis.mychess

import edu.austral.dissis.common.Position
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.mychess.factory.ChessBoardFactory

class MovementStrategy {

    fun moveTo(pieceToMove: Piece, toPosition: Position, board: Board): Board {
        val piecesPositionsCopy : MutableMap<Position, Piece> = board.getPiecesPositions().toMutableMap()
        val fromPosition : Position = board.getPositionByPiece(pieceToMove)
            if (pieceToMove.validator.validateMovement(board, Movement(pieceToMove, toPosition)) is SuccessfulResult){
                val targetPiece : Piece? = board.getPiecesPositions()[toPosition]
                if (targetPiece == null){
                    piecesPositionsCopy.remove(fromPosition)
                }
                if (targetPiece != null){
                    //come
                    piecesPositionsCopy.remove(fromPosition)
                    piecesPositionsCopy.remove(toPosition)
                }
                piecesPositionsCopy[toPosition] = pieceToMove
                piecesPositionsCopy.toMap()
                return ChessBoardFactory.createNewClassicBoard(piecesPositionsCopy, board)
            }
        return ChessBoardFactory.createNewClassicBoard(piecesPositionsCopy, board)
    }
}