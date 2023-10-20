package edu.austral.dissis.mychess.validator

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.result.FailureResult
import edu.austral.dissis.mychess.result.SuccessfulResult
import edu.austral.dissis.mychess.result.ValidatorResult
import kotlin.math.abs

class NoPiecesInPathValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition: Position = board.getPositionByPiece(movement.piece)
        val difRow: Int = (pieceActualPosition.y - movement.finalPosition.y)
        val difCol: Int = (pieceActualPosition.y - movement.finalPosition.x)

        if (movement.piece::class.simpleName == "Bishop" || movement.piece::class.simpleName == "Queen") {
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
                if (pieceInPath == null) {
                    return SuccessfulResult("There aren't a piece in path")
                }
            }
        }

        if (movement.piece::class.simpleName == "Rook" ) {
            // no salta piezas
            val diferencia: Int = abs(difRow) + abs(difCol)
            for (i in 1..diferencia) {
                val path = Position(
                    pieceActualPosition.x + i * (difCol / diferencia),
                    pieceActualPosition.y + i * (difRow / diferencia)
                )
                val pieceInPath: Piece = board.getPieceByPosition(path)
                if (pieceInPath == null) {
                    return SuccessfulResult("There aren't a piece in path")                }
            }
            return SuccessfulResult("There aren't a piece in path")
        }
        return FailureResult("There are a piece in path")
    }
}