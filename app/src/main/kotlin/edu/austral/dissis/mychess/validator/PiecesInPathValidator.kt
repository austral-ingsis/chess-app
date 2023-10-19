package edu.austral.dissis.mychess.validator

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.Piece
import kotlin.math.abs

class PiecesInPathValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val pieceActualPosition: Position = board.getPositionByPiece(movement.piece)
        val difRow: Int = (pieceActualPosition.y - movement.finalPosition.y)
        val difCol: Int = (pieceActualPosition.y - movement.finalPosition.x)

        if (movement.piece::class.simpleName == "Bishop") {
            var path: Position
            for (i in 1 until abs(difRow)) {
                path = Position(
                    pieceActualPosition.x + i * ((movement.finalPosition.x - pieceActualPosition.x)
                            / abs(difRow)),
                    pieceActualPosition.y + i * ((movement.finalPosition.y - pieceActualPosition.y)
                            / abs(difRow))
                )
                val pieceInPath: Piece? = board.getPiecesPositions()[path]
                // si hay una pieza en el path devuelve true
                if (pieceInPath != null) {
                    return true
                }
            }
        }

        if (movement.piece::class.simpleName == "Rook") {
            // no salta piezas
            val diferencia: Int = abs(difRow) + abs(difCol)
            for (i in 1..diferencia) {
                val path = Position(
                    pieceActualPosition.x + i * (difCol / diferencia),
                    pieceActualPosition.y + i * (difRow / diferencia)
                )
                val pieceInPath: Piece = board.getPieceByPosition(path)
                if (pieceInPath != null) {
                    return true
                }
            }
            return true
        }
        return false
    }

//    int diferencia = Math.abs(difRow) + Math.abs(difCol);
//            for (int i = 1; i <= diferencia; i++) {
//                Position path1 = new Position(movement.getPiece().getPosition().getX() + i * (difCol / diferencia), movement.getPiece().getPosition().getY() + i * (difRow / diferencia));
//                Piece pieceInPath = board.getPiece(path1);
//                if (pieceInPath != null){
//                    return false;
//                }
//            }
//            return true;

//    fun validateRule(board: Board, movement: Movement): Boolean {
//        val diagonalMove = Diagonal()
//        val target: Piece = board.getPiece(movement.getFinalPosition())
//        return if (diagonalMove.validateMove(board, movement)) {
//            if (target != null) {
//                if (target.getTeam().equals(movement.getPiece().getTeam())) {
//                    return false
//                }
//            }
//            //  el alfil no puede saltar piezas
//            val difRow: Int = Math.abs(movement.getPiece().getPosition().getY() - movement.getFinalPosition().getY())
//            var path: Position
//            for (i in 1 until difRow) {
//                path = Position(
//                    movement.getPiece().getPosition().getX() + i * ((movement.getFinalPosition()
//                        .getX() - movement.getPiece().getPosition().getX()) / difRow),
//                    movement.getPiece().getPosition().getY() + i * ((movement.getFinalPosition()
//                        .getY() - movement.getPiece().getPosition().getY()) / difRow)
//                )
//                val pieceInPath: Piece = board.getPiece(path)
//                if (pieceInPath != null) {
//                    return false
//                }
//            }
//            true
//        } else false
//    }
}