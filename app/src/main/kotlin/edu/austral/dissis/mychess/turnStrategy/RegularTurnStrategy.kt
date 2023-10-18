package edu.austral.dissis.mychess.turnStrategy

import edu.austral.dissis.mychess.piece.PieceColor

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