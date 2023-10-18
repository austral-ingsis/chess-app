package turnStrategy

import piece.PieceColor

interface TurnStrategy {
    fun advanceTurn(currentColor: PieceColor) : TurnStrategy
    fun getCurrentColor() : PieceColor
}