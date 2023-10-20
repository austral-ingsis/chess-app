package edu.austral.dissis.mychess.validator

import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.result.FailureResult
import edu.austral.dissis.mychess.result.SuccessfulResult
import edu.austral.dissis.mychess.result.ValidatorResult

class AndValidator(private val validators : List<Validator>) : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        validators.forEach { validator: Validator ->
            if (validator.validateMovement(board, movement)::class.simpleName == "FailureResult"){
                return FailureResult("At least one validator failed")
            }
        }
        return SuccessfulResult("All validators were successful")
    }
}