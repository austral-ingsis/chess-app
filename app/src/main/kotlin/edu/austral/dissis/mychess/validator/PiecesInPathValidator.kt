package validator

import piece.Piece
import Position
import board.Board
import kotlin.math.abs

class PiecesInPathValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        val difRow: Int = abs(pieceActualPosition.y - movement.finalPosition.y)
        var path: Position
        for (i in 1..difRow) {
            path = Position(
                pieceActualPosition.x + i * ((movement.finalPosition.x - pieceActualPosition.x)
                        / difRow),
                pieceActualPosition.y + i * ((movement.finalPosition.y - pieceActualPosition.y)
                        / difRow)
            )
            val pieceInPath : Piece? = board.getPiecesPositions().get(path)
            // si hay una pieza en el path devuelve true
            if (pieceInPath != null) {
                return true
            }
        }
        return false
    }
}