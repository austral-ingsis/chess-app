package gameState

import board.Board
import turnStrategy.TurnStrategy

class GameState(
    private val turnStrategy: TurnStrategy,
    private val boardsHistory: List<Board>
)  {

//    fun getCurrentBoard()

    fun getTurnStrategy(): TurnStrategy {
        return turnStrategy
    }

    fun getBoardsHistory(): List<Board> {
        return boardsHistory
    }

}
