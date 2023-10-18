package edu.austral.dissis.mychess.turnStrategy

import edu.austral.dissis.mychess.piece.PieceColor

interface TurnStrategy {
    fun advanceTurn(currentColor: PieceColor) : TurnStrategy
    fun getCurrentColor() : PieceColor
}