package edu.austral.dissis.common.result

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceColor

data class SuccessfulResult(
    val board: Board,
    val pieces: List<Piece>,
    val currentPlayer: PieceColor
) : Result