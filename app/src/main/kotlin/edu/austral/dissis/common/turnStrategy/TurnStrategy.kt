package edu.austral.dissis.common.turnStrategy

import edu.austral.dissis.common.piece.PieceColor

class TurnStrategy(private val color : PieceColor) {
    fun advanceTurn(currentColor: PieceColor): TurnStrategy {
        return if (currentColor == PieceColor.WHITE){
            TurnStrategy(PieceColor.BLACK)
        }else{
            TurnStrategy(PieceColor.WHITE)
        }
    }

    fun getCurrentColor(): PieceColor {
        return color
    }
}