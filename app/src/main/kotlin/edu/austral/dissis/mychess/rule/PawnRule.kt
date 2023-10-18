package edu.austral.dissis.mychess.rule
import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.piece.PieceColor
import edu.austral.dissis.mychess.rule.result.FailureRuleResult
import edu.austral.dissis.mychess.rule.result.SuccessfulRuleResult
import edu.austral.dissis.mychess.rule.result.RuleValidatorResult
import edu.austral.dissis.mychess.validator.*

class PawnRule : Rule {

    private val diagonalMovementValidator = DiagonalMovementValidator() //come
    private val verticalMovementValidator = VerticalMovementValidator() //se mueve
    private val piecesInPathValidator = PiecesInPathValidator()
    private val sameColorValidator = SameColorValidator()

    override fun isValidRule(board: Board, movement: Movement): RuleValidatorResult {
        val pieceTarget : Piece? = board.getPiecesPositions().get(movement.finalPosition)
        val pieceActualPosition : Position = board.getPositionByPiece(movement.piece)
        val incrementByColor : Int = if (movement.piece.getPieceColor().equals(PieceColor.WHITE)){ -1 } else 1
        val boardLimit : Int = if (movement.piece.getPieceColor().equals(PieceColor.WHITE)){
            board.getSizeY() -1
        }else{
            (board.getSizeY() - (board.getSizeY() - 2))
        }
        // movimiento normal, avanzan 1 para adelante
        if (verticalMovementValidator.validateMovement(board, movement)){
            if ((pieceActualPosition.y + incrementByColor == movement.finalPosition.y)){
                if (pieceTarget == null){
                    return SuccessfulRuleResult("Movimiento valido para el peon")
                }
            }
            // movimiento inicial
            else if ((pieceActualPosition.y == boardLimit) && (movement.finalPosition.y == boardLimit + 2 * incrementByColor)){
                if (pieceTarget != null){
                    return FailureRuleResult("No puede tomar esa posicion, hay otra pieza")
                }
                if (piecesInPathValidator.validateMovement(board, movement)){
                    return FailureRuleResult("El peon no puede saltar piezas")
                }
                return SuccessfulRuleResult("Movimiento inicial del peon")
            }
        }
        // movimiento para comer
        else if ((diagonalMovementValidator.validateMovement(board, movement))
            && (movement.finalPosition.y == pieceActualPosition.y + incrementByColor)){
            if (pieceTarget == null){
                return FailureRuleResult("No hay ninguna pieza para comer, movimiento invalido")
            }
            if (sameColorValidator.validateMovement(board, movement)){
                return FailureRuleResult("La pieza es del mismo equipo")
            }
            return SuccessfulRuleResult("Movimiento valido para comer")
        }
        return FailureRuleResult("No es movimiento valido para el peon")
    }
}
