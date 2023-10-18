package edu.austral.dissis.mychess.piece

import edu.austral.dissis.mychess.rule.Rule

interface Piece {
    fun getId() : String
    fun getPieceColor() : PieceColor
    fun getRuleList() : List<Rule>

}