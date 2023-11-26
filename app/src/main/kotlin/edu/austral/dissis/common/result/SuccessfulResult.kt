package edu.austral.dissis.common.result

import edu.austral.dissis.common.Game

data class SuccessfulResult(
    val game: Game
) : Result