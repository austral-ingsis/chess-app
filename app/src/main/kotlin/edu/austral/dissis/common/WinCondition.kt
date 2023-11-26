package edu.austral.dissis.common

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.result.Result

interface WinCondition {
    fun validateWinCondition(board: Board, movement: Movement): Result
}