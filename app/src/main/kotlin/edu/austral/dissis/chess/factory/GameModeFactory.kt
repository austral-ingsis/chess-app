package edu.austral.dissis.chess.factory

import checkers.CheckersTurn
import checkers.CheckersWin
import common.Board
import common.GameMode
import common.PieceImpl
import common.Position
import common.enums.Color
import common.enums.Type
import common.movement2.DiagonalMovement
import common.movement2.HorizontalAndVerticalMovement
import common.movement2.JumpMove
import common.turn.NormalTurn
import common.winningCondition.CheckMate
import edu.austral.dissis.chess.*
import edu.austral.dissis.chess.factory.BoardFactory.*

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
        val gameMode = GameMode(board , createBoardMovements(), createValidators(),NormalTurn() ,CheckMate())
        return gameMode
    }




}