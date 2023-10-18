package factory

import piece.Bishop
import rule.BishopRule
import piece.King
import rule.KingRule
import piece.Knight
import rule.KnightRule
import piece.Pawn
import rule.PawnRule
import piece.PieceColor
import piece.Queen
import edu.austral.dissis.mychess.rule.QueenRule
import piece.Rook
import rule.RookRule
import piece.Piece

class PieceFactory {

    companion object{
        private var id : Int = 0

        fun createKing(pieceColor: PieceColor) : Piece {
            return King("king" + id++, pieceColor, listOf(KingRule()))
        }

        fun createRook(pieceColor: PieceColor) : Piece {
            return Rook("rook" + id++, pieceColor, listOf(RookRule()))
        }

        fun createBishop(pieceColor: PieceColor) : Piece {
            return Bishop("bishop" + id++, pieceColor, listOf(BishopRule()))
        }

        fun createQueen(pieceColor: PieceColor) : Piece {
            return Queen("queen" + id++, pieceColor, listOf(QueenRule()))
        }

        fun createKnight(pieceColor: PieceColor) : Piece {
            return Knight("knight" + id++, pieceColor, listOf(KnightRule()))
        }

        fun createPawn(pieceColor: PieceColor) : Piece {
            return Pawn("pawn" + id++, pieceColor, listOf(PawnRule()))
        }
    }

}