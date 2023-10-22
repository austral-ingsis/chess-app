package edu.austral.dissis.mychess.turnStrategy

import edu.austral.dissis.mychess.piece.PieceColor

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