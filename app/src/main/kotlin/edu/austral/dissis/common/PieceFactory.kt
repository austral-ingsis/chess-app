package edu.austral.dissis.common

import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceColor

interface PieceFactory {
    fun createPiece(type: String, color: PieceColor, id: Int): Piece
}