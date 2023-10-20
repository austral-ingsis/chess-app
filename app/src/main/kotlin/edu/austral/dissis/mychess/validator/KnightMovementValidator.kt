package edu.austral.dissis.mychess.validator

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.result.FailureResult
import edu.austral.dissis.mychess.result.SuccessfulResult
import edu.austral.dissis.mychess.result.ValidatorResult
import kotlin.math.abs

class KnightMovementValidator: Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        // el caballo se mueve 1 en filas y 2 en columnas o viceversa.
        val difRow : Int = abs(pieceActualPosition.y - movement.finalPosition.y)
        val difCol : Int = abs(pieceActualPosition.x - movement.finalPosition.x)
        if ((difRow == 1 && difCol == 2) || (difRow == 2 && difCol == 1)){
            // come siempre
            return SuccessfulResult("Valid movement")
        }
        return FailureResult("Invalid movement")
    }
}