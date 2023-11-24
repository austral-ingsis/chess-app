package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.board.Board

interface Validator {
    fun validateMovement(board: Board, movement: Movement) : Boolean
}