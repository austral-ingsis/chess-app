package edu.austral.dissis.mychess.factory

import edu.austral.dissis.mychess.piece.*
import edu.austral.dissis.mychess.validator.*
import edu.austral.dissis.mychess.validator.commonValidators.*
import edu.austral.dissis.mychess.validator.specificValidators.*

class PieceFactory {

    companion object{
//        private var id : Int = 0

        fun createPieceFactoryMethod(pieceType: String, color: PieceColor, id : Int): Piece {
            return when (pieceType) {
                "pawn" -> createPawn(color, id)
                "rook" -> createRook(color, id)
                "king" -> createKing(color, id)
                "knight" -> createKnight(color, id)
                "bishop" -> createBishop(color, id)
                "queen" -> createQueen(color, id)
                "archbishop" -> createArchbishop(color, id)
                "chancellor" -> createChancellor(color, id)

                else -> throw IllegalArgumentException("Tipo de pieza desconocido: $pieceType")
            }
        }

        private fun createKing(pieceColor: PieceColor, id: Int) : Piece {
            return Piece("king$id", pieceColor,
                OrValidator(listOf(
                    AndValidator(listOf(DiagonalMovementValidator(), LimitKingMovementValidator(), DifferentColorValidator())),
                    AndValidator(listOf(VerticalMovementValidator(), LimitKingMovementValidator(), DifferentColorValidator())),
                    AndValidator(listOf(HorizontalMovementValidator(), LimitKingMovementValidator(), DifferentColorValidator())),
                    AndValidator(listOf(HorizontalMovementValidator(), LimitKingMovementValidator(), ToPositionIsEmpty())),
                    AndValidator(listOf(VerticalMovementValidator(), LimitKingMovementValidator(), ToPositionIsEmpty())),
                    AndValidator(listOf(DiagonalMovementValidator(), LimitKingMovementValidator(), ToPositionIsEmpty()))
                ))
            )
        }

        private fun createRook(pieceColor: PieceColor, id: Int) : Piece {
            return Piece(
                "rook$id", pieceColor,
                OrValidator(listOf(
                    AndValidator(listOf(VerticalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty())),
                    AndValidator(listOf(HorizontalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty())),
                    AndValidator(listOf(VerticalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator())),
                    AndValidator(listOf(HorizontalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator()))
                )))
        }

        private fun createBishop(pieceColor: PieceColor, id: Int) : Piece {
            return Piece(
                "bishop$id", pieceColor,
                OrValidator(listOf(
                    AndValidator(listOf(DiagonalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator())),
                    AndValidator(listOf(DiagonalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty()))
            )))
        }

        private fun createQueen(pieceColor: PieceColor, id: Int) : Piece {
            return Piece(
                "queen$id", pieceColor,
                OrValidator(listOf(
                    //validadores de la torre
                    OrValidator(listOf(
                        AndValidator(listOf(VerticalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty())),
                        AndValidator(listOf(HorizontalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty())),
                        AndValidator(listOf(VerticalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator())),
                        AndValidator(listOf(HorizontalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator()))
                )),
                    //validadores del alfil
                    OrValidator(listOf(
                        AndValidator(listOf(DiagonalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator())),
                        AndValidator(listOf(DiagonalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty()))
                )))))
        }

        private fun createKnight(pieceColor: PieceColor, id: Int) : Piece {
            return Piece("knight$id", pieceColor,
                OrValidator(listOf(
                    AndValidator(listOf(KnightMovementValidator(), DifferentColorValidator())),
                    AndValidator(listOf(KnightMovementValidator(), ToPositionIsEmpty()))
                )))
        }

        private fun createPawn(pieceColor: PieceColor, id: Int) : Piece {
            return Piece(
                "pawn$id", pieceColor,
                OrValidator(listOf(
                    AndValidator(listOf(PawnInitialMovementValidator(), VerticalMovementValidator(), NoPiecesInPathValidator())),
                    AndValidator(listOf(PawnRegularMovementValidator(), VerticalMovementValidator())),
                    AndValidator(listOf(DifferentColorValidator(), DiagonalMovementValidator(), ForwardPawnMovementToEatValidator()))))
            )
        }

        // revisar
        private fun createArchbishop(pieceColor: PieceColor, id: Int) : Piece {
            return Piece(
                "archbishop$id", pieceColor,
                OrValidator(listOf(
                    // validadores del alfil
                    OrValidator(listOf(
                    AndValidator(listOf(DiagonalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator())),
                    AndValidator(listOf(DiagonalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty()))
                )),
                    // validadores del caballo
                    OrValidator(listOf(
                        AndValidator(listOf(KnightMovementValidator(), DifferentColorValidator())),
                        AndValidator(listOf(KnightMovementValidator(), ToPositionIsEmpty()))
                    ))
                ))
            )
        }

        private fun createChancellor(pieceColor: PieceColor, id: Int) : Piece {
            return Piece(
                "chancellor$id", pieceColor,
                OrValidator(listOf(
                    // validadores de la torre
                    OrValidator(listOf(
                    AndValidator(listOf(VerticalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty())),
                    AndValidator(listOf(HorizontalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty())),
                    AndValidator(listOf(VerticalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator())),
                    AndValidator(listOf(HorizontalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator()))
                )),
                    // validadores del caballo
                    OrValidator(listOf(
                        AndValidator(listOf(KnightMovementValidator(), DifferentColorValidator())),
                        AndValidator(listOf(KnightMovementValidator(), ToPositionIsEmpty()))
                    ))
                ))
            )
        }
    }

}