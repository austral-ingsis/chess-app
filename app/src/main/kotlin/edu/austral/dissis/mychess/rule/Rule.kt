package edu.austral.dissis.mychess.rule
import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.PieceColor
import edu.austral.dissis.mychess.rule.result.RuleValidatorResult
import edu.austral.dissis.mychess.validator.Movement

interface Rule {
    fun isValidRule(board: Board, movement: Movement) : RuleValidatorResult
}
