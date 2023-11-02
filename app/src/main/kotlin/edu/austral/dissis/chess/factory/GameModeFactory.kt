package edu.austral.dissis.chess.factory

import checkers.CheckersTurn
import checkers.CheckersWin
import common.GameMode
import chess.program.src.main.NormalTurn
import chess.program.src.main.CheckMate
import edu.austral.dissis.chess.*

class GameModeFactory  {

    fun createCheckersGameMode(): GameMode {
        val board = createCheckersInitialChessBoard()
        val turn = CheckersTurn()
        val gameMode = GameMode(board, createCheckersBoardMovements(), createMyValidators(), turn, CheckersWin())
        return gameMode
    }

    fun createGameMode(): GameMode {
        val gameMode = GameMode(createInitialChessBoard(), createBoardMovements(), createValidators(), NormalTurn(), CheckMate())
        return gameMode
    }

    fun createButterflyGameMode(): GameMode{
        val board = createInitialChessBoard() // habria que crear el butterfly
        val gameMode = GameMode(board , createBoardMovements(), createValidators(), NormalTurn(), CheckMate())
        return gameMode
    }




}