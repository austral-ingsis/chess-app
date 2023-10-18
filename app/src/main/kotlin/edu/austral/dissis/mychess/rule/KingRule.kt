package edu.austral.dissis.mychess.rule
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.rule.result.FailureRuleResult
import edu.austral.dissis.mychess.rule.result.SuccessfulRuleResult
import edu.austral.dissis.mychess.rule.result.RuleValidatorResult
import edu.austral.dissis.mychess.validator.*

class KingRule : Rule {

    private val diagonalMovementValidator = DiagonalMovementValidator()
    private val verticalMovementValidator = VerticalMovementValidator()
    private val horizontalMovementValidator = HorizontalMovementValidator()
    private val sameColorValidator = SameColorValidator()
    private val limitKingMovementValidator = LimitKingMovementValidator()

    override fun isValidRule(board: Board, movement: Movement): RuleValidatorResult {
        if (diagonalMovementValidator.validateMovement(board, movement)
            || verticalMovementValidator.validateMovement(board, movement)
            || horizontalMovementValidator.validateMovement(board, movement)
        ) {
            if (limitKingMovementValidator.validateMovement(board, movement)) {
                // come en las mismas direcciones en las que se mueve.
                if (!sameColorValidator.validateMovement(board, movement)) {
                    return SuccessfulRuleResult("Movimiento valido")
                }
                //return FailureMovementResult("")
            }
            // chequear si esta amenazado o si ya se movio, en cualquiera de estos casos no lo deja hacer el enroque
        }
        return FailureRuleResult("No es movimiento horizontal, vertical o diagonal")
    }
}
