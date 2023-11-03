package edu.austral.dissis.common.commonValidators

import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.Position

data class Movement(val piece: Piece, val finalPosition: Position)