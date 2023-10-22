package edu.austral.dissis.mychess

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.mychess.validator.Movement

class MyEngine : GameEngine {

    private val game = Game()

    override fun init(): InitialState {
        return game.getAdapter().adaptGameStateToInitialState(game.init())
    }

    override fun applyMove(move: Move): MoveResult {
        val movement : Movement = game.getAdapter().translateMoveToMovement(move)
        return game.getAdapter().adaptMyMoveResultToMoveResult(game.applyMove(movement, game.getAdapter().getLastState()))
    }

}