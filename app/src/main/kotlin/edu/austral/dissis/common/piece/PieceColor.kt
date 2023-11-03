package edu.austral.dissis.common.piece
enum class PieceColor {
    BLACK, WHITE;

    companion object {
        fun fromInt(value: Int): PieceColor {
            return if (value == 0) BLACK else WHITE
        }
    }
}
