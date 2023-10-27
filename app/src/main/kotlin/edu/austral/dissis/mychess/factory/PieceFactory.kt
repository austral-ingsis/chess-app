package edu.austral.dissis.mychess.factory

import edu.austral.dissis.mychess.piece.*
import edu.austral.dissis.mychess.validator.*
import edu.austral.dissis.mychess.validator.specificValidators.*

class PieceFactory {

    companion object{
        private var id : Int = 0

        fun createKing(pieceColor: PieceColor) : Piece {
            return Piece("king" + id++, pieceColor,
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

        fun createRook(pieceColor: PieceColor) : Piece {
            return Piece("rook" + id++, pieceColor,
                OrValidator(listOf(
                    AndValidator(listOf(VerticalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty())),
                    AndValidator(listOf(HorizontalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty())),
                    AndValidator(listOf(VerticalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator())),
                    AndValidator(listOf(HorizontalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator()))
                )))
        }

        fun createBishop(pieceColor: PieceColor) : Piece {
            return Piece("bishop" + id++, pieceColor,
                OrValidator(listOf(
                    AndValidator(listOf(DiagonalMovementValidator(), NoPiecesInPathValidator(), DifferentColorValidator())),
                    AndValidator(listOf(DiagonalMovementValidator(), NoPiecesInPathValidator(), ToPositionIsEmpty()))
            )))
        }

        fun createQueen(pieceColor: PieceColor) : Piece {
            return Piece("queen" + id++, pieceColor,
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

        fun createKnight(pieceColor: PieceColor) : Piece {
            return Piece("knight" + id++, pieceColor,
                OrValidator(listOf(
                    AndValidator(listOf(KnightMovementValidator(), DifferentColorValidator())),
                    AndValidator(listOf(KnightMovementValidator(), ToPositionIsEmpty()))
                )))
        }

        fun createPawn(pieceColor: PieceColor) : Piece {
            return Piece("pawn" + id++, pieceColor,
                OrValidator(
                    listOf( AndValidator(listOf(PawnInitialMovementValidator(), VerticalMovementValidator())),
                        AndValidator(listOf(PawnRegularMovementValidator(), VerticalMovementValidator())),
                        AndValidator(listOf(DifferentColorValidator(), DiagonalMovementValidator()))))
            )
        }

        // revisar
        fun createArchbishop(pieceColor: PieceColor) : Piece {
            return Piece("archbishop" + id++, pieceColor, AndValidator(listOf(AndValidator(listOf(
                DiagonalMovementValidator(), OrValidator(
                listOf(NoPiecesInPathValidator(), DifferentColorValidator())))), AndValidator(listOf(
                DifferentColorValidator(),
                KnightMovementValidator()
            )))))
        }

        fun createChancellor(pieceColor: PieceColor) : Piece {
            return Piece("chancellor" + id++, pieceColor, AndValidator(listOf(AndValidator(listOf(OrValidator(listOf(
                VerticalMovementValidator(), HorizontalMovementValidator())), OrValidator(
                listOf(NoPiecesInPathValidator(), DifferentColorValidator())))),
                OrValidator(listOf(DifferentColorValidator(), KnightMovementValidator()))
            )))
        }
    }

}