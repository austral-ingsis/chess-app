package edu.austral.dissis.common

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.result.SuccessfulResult

class MovementStrategy(private val pieceFactory: PieceFactory) {

    fun moveTo(pieceToMove: Piece, toPosition: Position, board: Board): Board {
        val piecesPositionsCopy: MutableMap<Position, Piece> = board.getPiecesPositions().toMutableMap()
        val fromPosition: Position = board.getPositionByPiece(pieceToMove)
        val pieceActualPosition: Position = board.getPositionByPiece(pieceToMove)

        if (isValidMove(pieceToMove, toPosition, board)) {
            val middlePosition = Position(pieceActualPosition.x + (toPosition.x - pieceActualPosition.x) / 2, pieceActualPosition.y + (toPosition.y - pieceActualPosition.y) / 2)
            handleEmptyTargetPosition(piecesPositionsCopy, fromPosition, toPosition, pieceToMove, board)
            handleCapture(piecesPositionsCopy, fromPosition, toPosition, pieceToMove, middlePosition)
            handlePromotion(piecesPositionsCopy, toPosition, board, pieceToMove)
            return BoardFactory.createNewClassicBoard(piecesPositionsCopy, board)
        }
        return board
    }

    private fun isValidMove(pieceToMove: Piece, toPosition: Position, board: Board): Boolean{
        return pieceToMove.validator.validateMovement(board, Movement(pieceToMove, toPosition)) is SuccessfulResult
    }

    private fun handleEmptyTargetPosition(
        piecesPositionsCopy: MutableMap<Position, Piece>,
        fromPosition: Position,
        toPosition: Position,
        pieceToMove: Piece,
        board: Board
    ) {
        if (piecesPositionsCopy[toPosition] == null) {
            piecesPositionsCopy.remove(fromPosition)
            if (shouldPromote(pieceToMove, toPosition, board)) {
                promotePawn(pieceToMove, toPosition, piecesPositionsCopy)
            } else {
                piecesPositionsCopy[toPosition] = pieceToMove
            }
        }
    }

    private fun handleCapture(
        piecesPositionsCopy: MutableMap<Position, Piece>,
        fromPosition: Position,
        toPosition: Position,
        pieceToMove: Piece,
        middlePosition: Position
    ) {
        val targetPiece = piecesPositionsCopy[toPosition]
        val middlePiece = piecesPositionsCopy[middlePosition]

        if (targetPiece != null && targetPiece.color != pieceToMove.color) {
            piecesPositionsCopy.remove(fromPosition)
            piecesPositionsCopy.remove(toPosition)
            piecesPositionsCopy[toPosition] = pieceToMove
        }else if (middlePiece != null && (pieceToMove.id.takeWhile { it.isLetter() } == "pawn" || pieceToMove.id.takeWhile { it.isLetter() } == "queen")) {
            piecesPositionsCopy.remove(middlePosition)
        }
    }

    private fun handlePromotion(
        piecesPositionsCopy: MutableMap<Position, Piece>,
        toPosition: Position,
        board: Board,
        pieceToMove: Piece
    ) {
        if (shouldPromote(pieceToMove, toPosition, board)) {
            promotePawn(pieceToMove, toPosition, piecesPositionsCopy)
        }
    }

    private fun shouldPromote(pieceToMove: Piece, toPosition: Position, board: Board): Boolean {
        return (toPosition.y == 1 || toPosition.y == board.getSizeY()) &&
                (pieceToMove.id.takeWhile { it.isLetter() } == "pawn")
    }

    private fun promotePawn(pieceToMove: Piece, toPosition: Position, piecesPositionsCopy: MutableMap<Position, Piece>) {
        val pieceId = pieceToMove.id.filter { it.isDigit() }
        piecesPositionsCopy[toPosition] = pieceFactory.createPiece("queen", pieceToMove.color, pieceId.toInt())
    }



}