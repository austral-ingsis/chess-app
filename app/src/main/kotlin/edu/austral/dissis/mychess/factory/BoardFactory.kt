

import board.Board
import edu.austral.dissis.mychess.board.ClassicBoard
import factory.PieceFactory
import piece.Piece
import piece.PieceColor

class BoardFactory {

    companion object {
        fun createInitialClassicBoard(): Board {
            val positions: List<Position> = fillPositions()
            val piecesPositions: Map<Position, Piece> = mapOf()
            val mapWithPawns = placePawnsInitialPosition(piecesPositions)
            val mapWithRooks = placeRookInitialPosition(mapWithPawns)
            val mapWithKnights = placeKnightInitialPosition(mapWithRooks)
            val mapWithBishops = placeBishopInitialPosition(mapWithKnights)
            val mapWithQueens = placeQueenInitialPosition(mapWithBishops)
            val mapWithKings = placeKingInitialPosition(mapWithQueens)
            return ClassicBoard(8, 8, mapWithKings, positions)
        }

        fun createNewClassicBoard(piecesPositions : Map<Position, Piece>, board: Board): Board {
            return ClassicBoard(board.getSizeX(), board.getSizeY(), piecesPositions, board.getPositions())
        }

        private fun placePawnsInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val pawnMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            for(x in 1..8){
                pawnMap[Position(x, 2)] = PieceFactory.createPawn(PieceColor.BLACK)
                pawnMap[Position(x, 7)] = PieceFactory.createPawn(PieceColor.WHITE)
            }
            return pawnMap
        }

        private fun placeRookInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece>{
            val rookMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            rookMap[Position(1, 1)] = PieceFactory.createRook(PieceColor.BLACK)
            rookMap[Position(8, 1)] = PieceFactory.createRook(PieceColor.BLACK)
            rookMap[Position(1, 8)] = PieceFactory.createRook(PieceColor.WHITE)
            rookMap[Position(8, 8)] = PieceFactory.createRook(PieceColor.WHITE)
            return rookMap
        }

        private fun placeKnightInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val knightMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            knightMap[Position(2, 1)] = PieceFactory.createKnight(PieceColor.BLACK)
            knightMap[Position(7, 1)] = PieceFactory.createKnight(PieceColor.BLACK)
            knightMap[Position(2, 8)] = PieceFactory.createKnight(PieceColor.WHITE)
            knightMap[Position(7, 8)] = PieceFactory.createKnight(PieceColor.WHITE)
            return knightMap
        }

        private fun placeBishopInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val bishopMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            bishopMap[Position(3, 1)] = PieceFactory.createBishop(PieceColor.BLACK)
            bishopMap[Position(6, 1)] = PieceFactory.createBishop(PieceColor.BLACK)
            bishopMap[Position(3, 8)] = PieceFactory.createBishop(PieceColor.WHITE)
            bishopMap[Position(6, 8)] = PieceFactory.createBishop(PieceColor.WHITE)
            return bishopMap
        }

        private fun placeQueenInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val queenMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            queenMap[Position(4, 1)] = PieceFactory.createQueen(PieceColor.BLACK)
            queenMap[Position(4, 8)] = PieceFactory.createQueen(PieceColor.WHITE)
            return queenMap
        }

        private fun placeKingInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val kingMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            kingMap[Position(5, 1)] = PieceFactory.createKing(PieceColor.BLACK)
            kingMap[Position(5, 8)] = PieceFactory.createKing(PieceColor.WHITE)
            return kingMap
        }

        private fun fillPositions(): List<Position> {
            val positionsToReturn: MutableList<Position> = mutableListOf()
            for (i in 1..8) {
                for (j in 1..8) {
                    positionsToReturn.add(Position(i, j))
                }
            }
            return positionsToReturn
        }
    }


}