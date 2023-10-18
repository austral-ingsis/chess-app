package edu.austral.dissis.mychess.turnStrategy

import piece.PieceColor
import turnStrategy.TurnStrategy

class RegularTurnStrategy(private val color : PieceColor) : TurnStrategy {
    override fun advanceTurn(currentColor: PieceColor): TurnStrategy {
        return if (currentColor == PieceColor.WHITE){
            RegularTurnStrategy(PieceColor.BLACK)
        }else{
            RegularTurnStrategy(PieceColor.WHITE)
        }
    }

    override fun getCurrentColor(): PieceColor {
        return color
    }
}