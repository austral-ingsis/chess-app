package edu.austral.dissis.chess

import chess.program.src.boardMovement.BoardMovement
import chess.program.src.boardMovement.CastleMovement
import chess.program.src.boardMovement.replacement.InitialReplacementStrategy
import chess.program.src.boardMovement.replacement.PositionReplacementStrategy
import chess.program.src.boardMovement.replacement.ReplacementMovement
import chess.program.src.boardValidator.*
import common.enums.Color.*;
import common.enums.Type
import common.enums.Type.*;
import common.movement2.DiagonalMovement
import common.movement2.HorizontalAndVerticalMovement
import common.movement2.JumpMove
import common.movement2.Movement2
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



