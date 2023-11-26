package edu.austral.dissis.mychess.factory

import edu.austral.dissis.common.Game
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.mychess.ChessTurnStrategy
import edu.austral.dissis.mychess.ChessWinCondition

fun createClassicChessGame(): Game {
    return Game(
        ClassicChessBoardFactory().createInitialBoard(),
        PieceColor.WHITE,
        ChessTurnStrategy(PieceColor.WHITE),
        ChessWinCondition()
    )
}

fun createCapablancaChessGame(): Game{
    return Game(
        CapablancaChessBoardFactory().createInitialBoard(),
        PieceColor.WHITE,
        ChessTurnStrategy(PieceColor.WHITE),
        ChessWinCondition()
    )
}