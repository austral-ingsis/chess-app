package edu.austral.dissis.mychess.validator

import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.result.ValidatorResult

interface Validator {
    fun validateMovement(board: Board, movement: Movement) : ValidatorResult
}