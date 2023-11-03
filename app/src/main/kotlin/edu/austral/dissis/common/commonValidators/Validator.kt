package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.result.ValidatorResult

interface Validator {
    fun validateMovement(board: Board, movement: Movement) : ValidatorResult
}