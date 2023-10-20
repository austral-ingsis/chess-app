package edu.austral.dissis.mychess.piece

import edu.austral.dissis.mychess.validator.Validator

data class Piece(val id: String, val color: PieceColor, val validator: Validator)