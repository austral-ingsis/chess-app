package edu.austral.dissis.mychess.factory

import edu.austral.dissis.common.piece.*
import edu.austral.dissis.common.commonValidators.*
import edu.austral.dissis.mychess.validator.*

class ChessPieceFactory {

    companion object{
        fun createPiece(pieceType: String, color: PieceColor, id : Int): Piece {
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
                AndValidator(listOf(
                    OrValidator(listOf(
                    AndValidator(listOf(DiagonalMovementValidator(), LimitKingMovementValidator(), DifferentColorValidator())),
                    AndValidator(listOf(VerticalMovementValidator(), LimitKingMovementValidator(), DifferentColorValidator())),
                    AndValidator(listOf(HorizontalMovementValidator(), LimitKingMovementValidator(), DifferentColorValidator())),
                    AndValidator(listOf(HorizontalMovementValidator(), LimitKingMovementValidator(), ToPositionIsEmpty())),
                    AndValidator(listOf(VerticalMovementValidator(), LimitKingMovementValidator(), ToPositionIsEmpty())),
                    AndValidator(listOf(DiagonalMovementValidator(), LimitKingMovementValidator(), ToPositionIsEmpty()))
                )),
                    KingInCheckValidator()
                ))

            )
        }

        private fun createRook(pieceColor: PieceColor, id: Int) : Piece {
            return Piece(
                "rook$id", pieceColor,
                AndValidator(listOf(
                    OrValidator(listOf(
                    AndValidator(listOf(VerticalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty())),
                    AndValidator(listOf(HorizontalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty())),
                    AndValidator(listOf(VerticalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator())),
                    AndValidator(listOf(HorizontalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator()))
                )),
                    KingInCheckValidator()
                ))

            )
        }

        private fun createBishop(pieceColor: PieceColor, id: Int) : Piece {
            return Piece(
                "bishop$id", pieceColor,
                AndValidator(listOf(
                    OrValidator(listOf(
                    AndValidator(listOf(DiagonalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator())),
                    AndValidator(listOf(DiagonalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty()))
                )),
                    KingInCheckValidator()))

            )
        }

        private fun createQueen(pieceColor: PieceColor, id: Int) : Piece {
            return Piece(
                "queen$id", pieceColor,
                AndValidator(listOf(
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
                        ))
                    )),
                    KingInCheckValidator()
                ))

            )
        }

        private fun createKnight(pieceColor: PieceColor, id: Int) : Piece {
            return Piece("knight$id", pieceColor,
                AndValidator(listOf(
                    OrValidator(listOf(
                        AndValidator(listOf(KnightMovementValidator(), DifferentColorValidator())),
                        AndValidator(listOf(KnightMovementValidator(), ToPositionIsEmpty()))
                    )),
                    KingInCheckValidator()
                ))

            )
        }

        private fun createPawn(pieceColor: PieceColor, id: Int) : Piece {
            return Piece(
                "pawn$id", pieceColor,
                AndValidator(listOf(
                    OrValidator(listOf(
                        AndValidator(listOf(PawnInitialMovementValidator(), VerticalMovementValidator(), NoPiecesInPathValidator())),
                        AndValidator(listOf(PawnRegularMovementValidator(), VerticalMovementValidator())),
                        AndValidator(listOf(DifferentColorValidator(), DiagonalMovementValidator(), ForwardPawnMovementToEatValidator()))
                    )),
                    KingInCheckValidator()
                ))
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