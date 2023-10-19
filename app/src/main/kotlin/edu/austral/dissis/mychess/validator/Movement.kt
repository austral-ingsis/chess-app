package edu.austral.dissis.mychess.validator

import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.Position

data class Movement(val piece: Piece, val finalPosition: Position)