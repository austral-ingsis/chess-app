package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.validators.CaptureMovementValidator
import edu.austral.dissis.checkers.validators.CheckerQueenMovementValidator
import edu.austral.dissis.checkers.validators.RegularCheckerMovementValidator
import edu.austral.dissis.checkers.validators.WinCondition
import edu.austral.dissis.common.commonValidators.*
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceColor

class CheckersPieceFactory {

    companion object {
        fun createPiece(pieceType: String, color: PieceColor, id: Int): Piece {
            return when (pieceType) {
                "pawn" -> createPawn(color, id)
                "queen" -> createQueen(color, id)
                else -> throw IllegalArgumentException("Tipo de pieza desconocido: $pieceType")
            }
        }

        fun createQueen(color: PieceColor, id: Int): Piece {
            return Piece("queen$id", color,
                OrValidator(listOf(
                AndValidator(
                    listOf(DiagonalMovementValidator(), CaptureMovementValidator(), ToPositionIsEmpty())
                ),
                AndValidator(
                    listOf(DiagonalMovementValidator(), CheckerQueenMovementValidator(), ToPositionIsEmpty()))
//                    , WinCondition()
            )))
        }

        private fun createPawn(pieceColor: PieceColor, id: Int): Piece {
            return Piece(
                "pawn$id", pieceColor,
                OrValidator(listOf(
                    AndValidator(
                        listOf(DiagonalMovementValidator(), CaptureMovementValidator(), ToPositionIsEmpty())
                    ),
                    AndValidator(
                        listOf(DiagonalMovementValidator(), RegularCheckerMovementValidator(), ToPositionIsEmpty()))
//                    ,WinCondition()

                ))

            )
        }
    }
}