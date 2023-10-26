package edu.austral.dissis.chess

import checkers.CheckersWin
import checkers.Eat
import checkers.PriorityMovement
import chess.program.src.boardMovement.BoardMovement
import chess.program.src.boardMovement.replacement.PositionReplacementStrategy
import chess.program.src.boardMovement.replacement.ReplacementMovement
import chess.program.src.boardValidator.*
import common.Game
import common.*
import common.enums.Color
import common.enums.Type
import common.movement2.DiagonalMovement
import common.movement2.strategyMovement.DiagonalObligatoryTrepassin
import common.movement2.strategyMovement.NoEatStrategy
import checkers.CheckersTurn
import checkers.ComposeBoardMovement
import edu.austral.dissis.chess.factory.BoardFactory
import edu.austral.dissis.chess.factory.BoardMovementsFactory
import edu.austral.dissis.chess.factory.BoardValidatorsFactory
import edu.austral.dissis.chess.factory.GameFactory


fun createCheckersInitialChessBoard(): Board {
    return BoardFactory().createCheckersInitialChessBoard()
}


    fun createCheckersGame(gm: GameMode): Game {
        return GameFactory().createNormalGame(gm)
    }


    fun createMyValidators(): List<Validator> {
            return BoardValidatorsFactory().createCheckersValidators()
        }

    fun createCheckersBoardMovements(): List<BoardMovement> {
        return BoardMovementsFactory().createCheckersBoardMovements()
    }

    fun createCheckersGameMode(): GameMode {
        val board = createCheckersInitialChessBoard();
        val turn = CheckersTurn(board)
        val gameMode = GameMode(board, createCheckersBoardMovements(), createMyValidators(), turn, CheckersWin())
        return gameMode
    }




