package edu.austral.dissis.mychess.rule
import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.validator.SameColorValidator
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.rule.result.FailureRuleResult
import edu.austral.dissis.mychess.rule.result.SuccessfulRuleResult
import edu.austral.dissis.mychess.rule.result.RuleValidatorResult
import edu.austral.dissis.mychess.validator.Movement
import kotlin.math.abs

class KnightRule : Rule {

    private val sameColorValidator = SameColorValidator()
    override fun isValidRule(board: Board, movement: Movement): RuleValidatorResult {
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        // el caballo se mueve 1 en filas y 2 en columnas o viceversa.
        val difRow : Int = abs(pieceActualPosition.y - movement.finalPosition.y)
        val difCol : Int = abs(pieceActualPosition.x - movement.finalPosition.x)
        if ((difRow == 1 && difCol == 2) || (difRow == 2 && difCol == 1)){
            // come siempre
            if (!sameColorValidator.validateMovement(board, movement)){
                return SuccessfulRuleResult("Movimiento valido")
            }
        }
        return FailureRuleResult("No es un movimiento valido para el caballo")
    }

}
