package edu.austral.dissis.common.turnStrategy

import edu.austral.dissis.common.piece.PieceColor

class ClassicTurnStrategy(private val color : PieceColor) : TurnStrategy {
    override fun advanceTurn(currentColor: PieceColor): TurnStrategy {
        return if (currentColor == PieceColor.WHITE){
            ClassicTurnStrategy(PieceColor.BLACK)
        }else{
            ClassicTurnStrategy(PieceColor.WHITE)
        }
    }

    override fun getCurrentColor(): PieceColor {
        return color
    }
}