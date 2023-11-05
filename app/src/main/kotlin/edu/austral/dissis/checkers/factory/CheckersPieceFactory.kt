package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.WinCondition
import edu.austral.dissis.checkers.validators.CaptureMovementValidator
import edu.austral.dissis.checkers.validators.RegularCheckerMovementValidator
import edu.austral.dissis.common.commonValidators.*
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceColor

class CheckersPieceFactory {

    companion object {
        fun createPiece(pieceType: String, color: PieceColor, id: Int): Piece {
            return when (pieceType) {
                "pawn" -> createPawn(color, id)
                else -> throw IllegalArgumentException("Tipo de pieza desconocido: $pieceType")
            }
        }

        private fun createPawn(pieceColor: PieceColor, id: Int): Piece {
            return Piece(
                "pawn$id", pieceColor,
                OrValidator(listOf(
                    AndValidator(
                        listOf(DiagonalMovementValidator(), CaptureMovementValidator(), ToPositionIsEmpty())
                    ),
                    AndValidator(listOf(DiagonalMovementValidator(), RegularCheckerMovementValidator(), ToPositionIsEmpty()))
//                    ,WinCondition()
                ))

            )
        }
    }
}