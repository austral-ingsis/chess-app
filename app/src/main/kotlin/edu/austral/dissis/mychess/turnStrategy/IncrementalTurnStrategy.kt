package turnStrategy

import piece.PieceColor

class IncrementalTurnStrategy(private val color : PieceColor) : TurnStrategy {

    override fun advanceTurn(currentColor: PieceColor): TurnStrategy {
        TODO("Not yet implemented")
    }

    override fun getCurrentColor(): PieceColor {
        TODO("Not yet implemented")
    }
}