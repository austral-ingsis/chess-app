package edu.austral.dissis.mychess

import edu.austral.dissis.mychess.factory.BoardFactory
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.validator.Movement

class MovementStrategy {

    fun moveTo(pieceToMove: Piece, toPosition: Position, board: Board): Board {
        val piecesPositionsCopy : MutableMap<Position, Piece> = board.getPiecesPositions().toMutableMap()
        val fromPosition : Position = board.getPositionByPiece(pieceToMove)
            if (pieceToMove.validator.validateMovement(board, Movement(pieceToMove, toPosition))::class.simpleName == "SuccessfulResult"){
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
                return BoardFactory.createNewClassicBoard(piecesPositionsCopy, board)
            }
        return BoardFactory.createNewClassicBoard(piecesPositionsCopy, board)
    }
}