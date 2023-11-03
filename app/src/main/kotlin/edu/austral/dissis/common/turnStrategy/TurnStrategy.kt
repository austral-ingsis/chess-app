package edu.austral.dissis.common.turnStrategy

import edu.austral.dissis.common.piece.PieceColor

interface TurnStrategy {
    fun advanceTurn(currentColor: PieceColor) : TurnStrategy
    fun getCurrentColor() : PieceColor
}