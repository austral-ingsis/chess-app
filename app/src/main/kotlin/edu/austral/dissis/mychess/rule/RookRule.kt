package edu.austral.dissis.mychess.rule
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.rule.result.FailureRuleResult
import edu.austral.dissis.mychess.rule.result.SuccessfulRuleResult
import edu.austral.dissis.mychess.rule.result.RuleValidatorResult
import edu.austral.dissis.mychess.validator.*

class RookRule : Rule {

    private val verticalMovementValidator = VerticalMovementValidator()
    private val horizontalMovementValidator = HorizontalMovementValidator()
    private val piecesInPathValidator = PiecesInPathValidator()
    private val sameColorValidator = SameColorValidator()

    override fun isValidRule(board: Board, movement: Movement): RuleValidatorResult {
        val pieceTarget : Piece? = board.getPiecesPositions().get(movement.finalPosition)
        if (verticalMovementValidator.validateMovement(board, movement)
            || horizontalMovementValidator.validateMovement(board, movement)){
            if (pieceTarget != null){
                if (piecesInPathValidator.validateMovement(board, movement)){
                    return FailureRuleResult("No puede saltar piezas")
                }
                return if (sameColorValidator.validateMovement(board, movement)){
                    FailureRuleResult("No puede comer una pieza de su mismo equipo")
                }else SuccessfulRuleResult("Movimiento valido para comer")
            }else return SuccessfulRuleResult("Movimiento valido")

        }
        return FailureRuleResult("No corresponde a un movimiento vertical u horizontal")
    }
}
