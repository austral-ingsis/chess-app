package edu.austral.dissis.common

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.SuccessfulResult

class Engine : GameEngine{
    private val adapter = Adapter()
    private val game = Game()

    override fun init(): InitialState {
        val initialStateResult = game.init()
        return adapter.adaptGameStateToInitialState(initialStateResult)
}

    override fun applyMove(move: Move): MoveResult {
        val from = adapter.adaptPositionToMyPosition(move.from)
        val to = adapter.adaptPositionToMyPosition(move.to)
        return when (val result = game.applyMove(Movement(from, to))){
            is FailureResult -> InvalidMove(result.reason)
            is SuccessfulResult -> {
                val chessPieces = adapter.adaptPiecesToChessPieces(result.board, result.pieces)
                val currentPlayer = adapter.adaptPieceColorToPlayerColor(result.currentPlayer)

                NewGameState(chessPieces, currentPlayer)
            }
        }
    }
}
