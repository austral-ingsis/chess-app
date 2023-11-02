package edu.austral.dissis.chess

import common.boardMovement.BoardMovement
import common.Game
import common.*
import common.boardValidator.Validator
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




