package edu.austral.dissis.mychess.rule
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.rule.result.FailureRuleResult
import edu.austral.dissis.mychess.rule.result.SuccessfulRuleResult
import edu.austral.dissis.mychess.rule.result.RuleValidatorResult
import edu.austral.dissis.mychess.validator.Movement

class QueenRule : Rule {

    private val rookRule : Rule = RookRule()
    private val bishopRule : Rule = BishopRule()

    override fun isValidRule(board: Board, movement: Movement): RuleValidatorResult {
        return if (rookRule.isValidRule(board, movement)::class.simpleName == "SuccessfulRuleResult"){
            SuccessfulRuleResult("Cumple las reglas de la torre.")
        }else if (bishopRule.isValidRule(board, movement)::class.simpleName == "SuccessfulRuleResult"){
            SuccessfulRuleResult("Cumple las reglas del alfil.")
        }else FailureRuleResult("No cumple las reglas del alfil o de la torre")
    }
}
