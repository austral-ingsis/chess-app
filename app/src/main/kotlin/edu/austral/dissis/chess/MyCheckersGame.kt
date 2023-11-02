package edu.austral.dissis.chess

import checkers.CheckersWin
import chess.program.src.boardMovement.BoardMovement
import chess.program.src.boardValidator.*
import common.Game
import common.*
import checkers.CheckersTurn
import edu.austral.dissis.chess.factory.*


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

    fun createCheckersGM(): GameMode {
        return GameModeFactory().createCheckersGameMode()
    }




