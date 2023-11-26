package edu.austral.dissis.mychess

import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.turnStrategy.TurnStrategy

class ChessTurnStrategy(private val color : PieceColor): TurnStrategy {
    override fun advanceTurn(currentColor: PieceColor): TurnStrategy {
        return if (currentColor == PieceColor.WHITE){
            ChessTurnStrategy(PieceColor.BLACK)
        }else{
            ChessTurnStrategy(PieceColor.WHITE)
        }
    }

    override fun getCurrentColor(): PieceColor {
        return color
    }
}