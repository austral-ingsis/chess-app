package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.CheckersWinCondition
import edu.austral.dissis.common.Game
import edu.austral.dissis.common.piece.PieceColor

fun createClassicCheckersGame() : Game {
    return Game(
        CheckersBoardFactory().createInitialBoard(),
        PieceColor.WHITE,
        CheckersWinCondition()
    )
}