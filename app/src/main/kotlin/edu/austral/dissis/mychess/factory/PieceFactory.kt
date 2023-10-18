package edu.austral.dissis.mychess.factory

import edu.austral.dissis.mychess.piece.Bishop
import edu.austral.dissis.mychess.rule.BishopRule
import edu.austral.dissis.mychess.piece.King
import edu.austral.dissis.mychess.rule.KingRule
import edu.austral.dissis.mychess.piece.Knight
import edu.austral.dissis.mychess.rule.KnightRule
import edu.austral.dissis.mychess.piece.Pawn
import edu.austral.dissis.mychess.rule.PawnRule
import edu.austral.dissis.mychess.piece.PieceColor
import edu.austral.dissis.mychess.piece.Queen
import edu.austral.dissis.mychess.rule.QueenRule
import edu.austral.dissis.mychess.piece.Rook
import edu.austral.dissis.mychess.rule.RookRule
import edu.austral.dissis.mychess.piece.Piece

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