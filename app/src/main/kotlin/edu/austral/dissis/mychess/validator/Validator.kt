package edu.austral.dissis.mychess.validator

import edu.austral.dissis.mychess.board.Board

interface Validator {
    fun validateMovement(board: Board, movement: Movement) : Boolean
}