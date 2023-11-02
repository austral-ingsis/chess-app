package edu.austral.dissis.chess

import common.boardMovement.BoardMovement
import common.*
import common.boardValidator.Validator
import edu.austral.dissis.chess.factory.*


fun createInitialChessBoard(): Board {
        return BoardFactory().createInitialChessBoard()
    }

    fun createTestChessBoard(): Board {
        return BoardFactory().createTestChessBoard()
    }


    fun createGame(gm: GameMode): Game {
        return GameFactory().createNormalGame(gm)
    }


    fun createBoardMovements(): List<BoardMovement> {
        return BoardMovementsFactory().createChessBoardMovements()
    }

    fun createValidators(): List<Validator> {
        return BoardValidatorsFactory().createChessValidators()
    }

    fun createGameMode(): GameMode {
        return GameModeFactory().createGameMode()
    }

    fun createButterflyGameMode(): GameMode{
        return GameModeFactory().createButterflyGameMode()
    }




