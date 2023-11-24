package edu.austral.dissis.common.piece

import edu.austral.dissis.common.commonValidators.Validator

data class Piece(val id: String, val color: PieceColor, val pieceType: PieceType, val validator: Validator)