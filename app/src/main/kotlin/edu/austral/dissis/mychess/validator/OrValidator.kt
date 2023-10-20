package edu.austral.dissis.mychess.validator

import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.result.FailureResult
import edu.austral.dissis.mychess.result.SuccessfulResult
import edu.austral.dissis.mychess.result.ValidatorResult

class OrValidator(private val validators : List<Validator>) : Validator {
    override fun validateMovement(board: Board, movement: Movement): ValidatorResult {
        validators.forEach { validator: Validator ->
            if (validator.validateMovement(board, movement)::class.simpleName == "SuccessfulResult"){
                return SuccessfulResult("At least one validator was successful")
            }
        }
        return FailureResult("All validators failed")
    }
}