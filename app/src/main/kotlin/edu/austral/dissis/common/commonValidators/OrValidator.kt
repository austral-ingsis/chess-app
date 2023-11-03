package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.result.ValidatorResult

class OrValidator(private val validators : List<Validator>) : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        validators.forEach { validator: Validator ->
            if (validator.validateMovement(board, movement) is SuccessfulResult){
                return SuccessfulResult("At least one validator was successful")
            }
        }
        return FailureResult("All validators failed")
    }
}