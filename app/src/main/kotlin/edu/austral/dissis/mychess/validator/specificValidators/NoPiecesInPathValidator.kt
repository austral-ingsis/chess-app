package edu.austral.dissis.mychess.validator.specificValidators

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.result.FailureResult
import edu.austral.dissis.mychess.result.SuccessfulResult
import edu.austral.dissis.mychess.result.ValidatorResult
import edu.austral.dissis.mychess.validator.Movement
import edu.austral.dissis.mychess.validator.Validator
import kotlin.math.abs

class NoPiecesInPathValidator : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition: Position = board.getPositionByPiece(movement.piece)

        //  el alfil no puede saltar piezas
        val difRowBishop: Int = abs(pieceActualPosition.y - movement.finalPosition.y)
        val pieceName : String = movement.piece.id.takeWhile { it.isLetter() }
        if (pieceName == "bishop" || pieceName == "queen") {
            for (i in 1 until difRowBishop) {
                val path = Position(
                    pieceActualPosition.x + i * ((movement.finalPosition.x - pieceActualPosition.x) / difRowBishop),
                    pieceActualPosition.y + i * ((movement.finalPosition.y - pieceActualPosition.y) / difRowBishop)
                )
                val pieceInPath= board.getPiecesPositions()[path]
                if (pieceInPath != null) {
                    return FailureResult("")
                }
            }
            return SuccessfulResult("")
        }
        if (pieceName == "rook" || pieceName == "queen") {
            val difRow: Int = ( movement.finalPosition.y - pieceActualPosition.y)
            val difCol: Int = ( movement.finalPosition.x - pieceActualPosition.x)
            // no salta piezas
            val diferencia: Int = abs(difRow) + abs(difCol)
            for (i in 1..diferencia) {
                val path = Position(
                    pieceActualPosition.x + i * (difCol / diferencia),
                    pieceActualPosition.y + i * (difRow / diferencia)
                )
                val pieceInPath = board.getPiecesPositions()[path]
                if (pieceInPath != null) {
                    return FailureResult("There are a piece in path")
                }
            }
            return SuccessfulResult("There aren't a piece in path")
        }
        return FailureResult("There are a piece in path")
    }
}