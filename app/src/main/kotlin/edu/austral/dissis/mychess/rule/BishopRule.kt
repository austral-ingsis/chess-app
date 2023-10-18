package edu.austral.dissis.mychess.rule
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.validator.SameColorValidator
import edu.austral.dissis.mychess.rule.result.FailureRuleResult
import edu.austral.dissis.mychess.rule.result.SuccessfulRuleResult
import edu.austral.dissis.mychess.rule.result.RuleValidatorResult
import edu.austral.dissis.mychess.validator.DiagonalMovementValidator
import edu.austral.dissis.mychess.validator.Movement
import edu.austral.dissis.mychess.validator.PiecesInPathValidator

class BishopRule : Rule {

    private val diagonalMovementValidator = DiagonalMovementValidator()
    private val piecesInPathValidator = PiecesInPathValidator()
    private val sameColorValidator = SameColorValidator()

    override fun isValidRule(board: Board, movement: Movement): RuleValidatorResult {
        if (diagonalMovementValidator.validateMovement(board, movement)){
            if (!sameColorValidator.validateMovement(board, movement)){
                return SuccessfulRuleResult("Movimiento valido")
            }
            // el alfil no salta piezas
            return if (piecesInPathValidator.validateMovement(board, movement)){
                FailureRuleResult("El alfil no salta piezas")
            }else SuccessfulRuleResult("Movimiento valido")
        }
        return FailureRuleResult("No es movimiento diagonal.")
    }
}
