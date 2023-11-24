package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.board.Board

class OrValidator(private val validators : List<Validator>) : Validator {
    override fun validateMovement(board: Board, movement: Movement): Boolean {
        validators.forEach { validator: Validator ->
            if (validator.validateMovement(board, movement)){
                return true
            }
        }
        return false
    }
}