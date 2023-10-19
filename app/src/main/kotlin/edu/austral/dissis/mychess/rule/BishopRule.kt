package edu.austral.dissis.mychess.rule
import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.PieceColor
import edu.austral.dissis.mychess.validator.SameColorValidator
import edu.austral.dissis.mychess.rule.result.FailureRuleResult
import edu.austral.dissis.mychess.rule.result.SuccessfulRuleResult
import edu.austral.dissis.mychess.rule.result.RuleValidatorResult
import edu.austral.dissis.mychess.validator.DiagonalMovementValidator
import edu.austral.dissis.mychess.validator.Movement
import edu.austral.dissis.mychess.validator.PiecesInPathValidator
import java.lang.RuntimeException

class BishopRule : Rule {

    private val diagonalMovementValidator = DiagonalMovementValidator()
    private val piecesInPathValidator = PiecesInPathValidator()
    private val sameColorValidator = SameColorValidator()

    override fun isValidRule(board: Board, movement: Movement): RuleValidatorResult {
        if (diagonalMovementValidator.validateMovement(board, movement)){
            if (piecesInPathValidator.validateMovement(board, movement)){
                return FailureRuleResult("El alfil no salta piezas")
            }
            if (!sameColorValidator.validateMovement(board, movement)){
                return SuccessfulRuleResult("Movimiento valido")
            }
//            else SuccessfulRuleResult("Movimiento valido")
        }
        return FailureRuleResult("No es movimiento diagonal.")
    }

}
