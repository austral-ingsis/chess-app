package edu.austral.dissis.common.result

import edu.austral.dissis.common.piece.PieceColor

data class GameOver(val color: PieceColor) : ValidatorResult