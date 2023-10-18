package edu.austral.dissis.mychess

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.mychess.validator.Movement

class MyEngine : GameEngine {

    private val engine = Engine()

    override fun init(): InitialState {
        return engine.getAdapter().adaptGameStateToInitialState(engine.init())
    }

    override fun applyMove(move: Move): MoveResult {
        val movement : Movement = engine.getAdapter().translateMoveToMovement(move)
        return engine.getAdapter().adaptMyMoveResultToMoveResult(engine.applyMove(movement, engine.getAdapter().getLastState()))
    }

}