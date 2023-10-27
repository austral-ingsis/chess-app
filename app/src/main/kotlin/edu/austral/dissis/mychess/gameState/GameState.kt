package edu.austral.dissis.mychess.gameState

import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.turnStrategy.TurnStrategy

class GameState(
    private val turnStrategy: TurnStrategy,
    private val boardsHistory: List<Board>
)  {

    fun getTurnStrategy(): TurnStrategy {
        return turnStrategy
    }

    fun getBoardsHistory(): List<Board> {
        return boardsHistory
    }

    fun getLastBoard(): Board{
        return boardsHistory.last()
    }

}
