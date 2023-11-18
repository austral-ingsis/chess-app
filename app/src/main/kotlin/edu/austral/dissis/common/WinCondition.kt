package edu.austral.dissis.common

import edu.austral.dissis.chess.gui.MoveResult
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement

interface WinCondition {
    fun validateMovement(board: Board, movement: Movement): MoveResult
}