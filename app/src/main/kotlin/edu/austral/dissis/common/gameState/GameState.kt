package edu.austral.dissis.common.gameState

import edu.austral.dissis.common.WinCondition
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.turnStrategy.TurnStrategy

class GameState(
    private val turnStrategy: TurnStrategy,
    private val boardsHistory: List<Board>,
    private val winCondition: WinCondition
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

    fun getWinCondition(): WinCondition{
        return winCondition
    }

}
