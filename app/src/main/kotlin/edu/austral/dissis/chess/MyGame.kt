package edu.austral.dissis.chess

import chess.program.src.boardMovement.BoardMovement
import chess.program.src.boardValidator.*
import common.*
import common.turn.NormalTurn
import common.winningCondition.CheckMate
import edu.austral.dissis.chess.factory.BoardFactory
import edu.austral.dissis.chess.factory.BoardMovementsFactory
import edu.austral.dissis.chess.factory.BoardValidatorsFactory
import edu.austral.dissis.chess.factory.GameFactory


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
        val gameMode = GameMode(createInitialChessBoard(), createBoardMovements(), createValidators(), NormalTurn(), CheckMate())
        return gameMode
    }



