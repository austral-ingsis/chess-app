

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.ReadYaml
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.board.ClassicBoard
import edu.austral.dissis.mychess.factory.PieceFactory
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.piece.PieceColor

class BoardFactory {

    companion object {
        fun createInitialClassicBoard(): Board {
            val boardSize = 8
            val positions: List<Position> = fillPositions(boardSize)
            val piecesPositions: Map<Position, Piece> = mapOf()
            val mapWithPawns = placePawnsInitialPosition(piecesPositions)
            val mapWithRooks = placeRookClassicInitialPosition(mapWithPawns)
            val mapWithKnights = placeKnightClassicInitialPosition(mapWithRooks)
            val mapWithBishops = placeBishopClassicInitialPosition(mapWithKnights)
            val mapWithQueens = placeQueenClassicInitialPosition(mapWithBishops)
            val mapWithKings = placeKingClassicInitialPosition(mapWithQueens)
            return ClassicBoard(boardSize, boardSize, mapWithKings, positions)
        }

        private fun placePawnsInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val pawnMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml("classic_initial_positions.yml")
            val pawns = data["pawns"] as List<Map<String, List<List<Int>>>>
            val blackPawnPositions = pawns[0]["black_pawn_positions"]
            val blackPawnInitialPositionsParsed = blackPawnPositions?.map { createPositionList(it) }
            for (position in blackPawnInitialPositionsParsed!!){
                pawnMap[position] = PieceFactory.createPawn(PieceColor.BLACK)
            }
            val whitePawnPositions = pawns[1]["white_pawn_positions"]
            val whitePawnInitialPositionsParsed = whitePawnPositions?.map { createPositionList(it) }
            for (position in whitePawnInitialPositionsParsed!!) {
                pawnMap[position] = PieceFactory.createPawn(PieceColor.WHITE)
            }
            return pawnMap
        }

        private fun createPositionList(coordinates: List<Int>): Position {
            if (coordinates.size == 2) {
                return Position(coordinates[0], coordinates[1])
            } else {
                throw IllegalArgumentException("Coordinates must have exactly two values.")
            }
        }

        private fun placeRookClassicInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val rookMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml("classic_initial_positions.yml")
            val rooks = data["rooks"] as List<Map<String, List<List<Int>>>>
            val blackRooksInitialPositions = rooks[0]["black_rooks_positions"]
            val blackPawnInitialPositionsParsed = blackRooksInitialPositions?.map { createPositionList(it) }
            for (position in blackPawnInitialPositionsParsed!!){
                rookMap[position] = PieceFactory.createRook(PieceColor.BLACK)
            }
            val whiteRooksInitialPositions = rooks[1]["white_rooks_positions"]
            val whiteRooksInitialPositionsParsed = whiteRooksInitialPositions?.map { createPositionList(it) }
            for (position in whiteRooksInitialPositionsParsed!!){
                rookMap[position] = PieceFactory.createRook(PieceColor.WHITE)
            }
            return rookMap
        }

        private fun placeKnightClassicInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val knightMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml("classic_initial_positions.yml")
            val knights = data["knights"] as List<Map<String, List<List<Int>>>>
            val blackKnightsInitialPositions = knights[0]["black_knights_positions"]
            val blackKnightsInitialPositionsParsed = blackKnightsInitialPositions?.map { createPositionList(it) }
            for (position in blackKnightsInitialPositionsParsed!!){
                knightMap[position] = PieceFactory.createKnight(PieceColor.BLACK)
            }
            val whiteKnightsInitialPositions = knights[1]["white_knights_positions"]
            val whiteKnightsInitialPositionsParsed = whiteKnightsInitialPositions?.map { createPositionList(it) }
            for (position in whiteKnightsInitialPositionsParsed!!){
                knightMap[position] = PieceFactory.createKnight(PieceColor.WHITE)
            }
            return knightMap
        }

        private fun placeBishopClassicInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val bishopMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml("classic_initial_positions.yml")
            val bishops = data["bishops"] as List<Map<String, List<List<Int>>>>
            val blackBishopsInitialPositions = bishops[0]["black_bishops_positions"]
            val blackBishopsInitialPositionsParsed = blackBishopsInitialPositions?.map { createPositionList(it) }
            for (position in blackBishopsInitialPositionsParsed!!){
                bishopMap[position] = PieceFactory.createBishop(PieceColor.BLACK)
            }
            val whiteKnightsInitialPositions = bishops[1]["white_bishops_positions"]
            val whiteKnightsInitialPositionsParsed = whiteKnightsInitialPositions?.map { createPositionList(it) }
            for (position in whiteKnightsInitialPositionsParsed!!){
                bishopMap[position] = PieceFactory.createBishop(PieceColor.WHITE)
            }
            return bishopMap
        }

        private fun placeQueenClassicInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val queenMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml("classic_initial_positions.yml")
            val queens = data["queens"] as List<Map<String, List<List<Int>>>>
            val blackQueenInitialPositions = queens[0]["black_queen_position"]
            val blackQueenInitialPositionsParsed = blackQueenInitialPositions?.map { createPositionList(it) }
            for (position in blackQueenInitialPositionsParsed!!){
                queenMap[position] = PieceFactory.createQueen(PieceColor.BLACK)
            }
            val whiteQueenInitialPositions = queens[1]["white_queen_position"]
            val whiteQueenInitialPositionsParsed = whiteQueenInitialPositions?.map { createPositionList(it) }
            for (position in whiteQueenInitialPositionsParsed!!){
                queenMap[position] = PieceFactory.createQueen(PieceColor.WHITE)
            }
            return queenMap
        }

        private fun placeKingClassicInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val kingMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml("classic_initial_positions.yml")
            val kings = data["kings"] as List<Map<String, List<List<Int>>>>
            val blackKingInitialPositions = kings[0]["black_king_position"]
            val blackKingInitialPositionsParsed = blackKingInitialPositions?.map { createPositionList(it) }
            for (position in blackKingInitialPositionsParsed!!){
                kingMap[position] = PieceFactory.createKing(PieceColor.BLACK)
            }
            val whiteKingInitialPositions = kings[1]["white_king_position"]
            val whiteKingInitialPositionsParsed = whiteKingInitialPositions?.map { createPositionList(it) }
            for (position in whiteKingInitialPositionsParsed!!){
                kingMap[position] = PieceFactory.createKing(PieceColor.WHITE)
            }
            return kingMap
        }

        fun createNewClassicBoard(piecesPositions: Map<Position, Piece>, board: Board): Board {
            return ClassicBoard(board.getSizeX(), board.getSizeY(), piecesPositions, board.getPositions())
        }

        fun createInitialCapablancaBoard(): Board {
            val boardSize = 10
            val positions: List<Position> = fillPositions(boardSize)
            val piecesPositions: Map<Position, Piece> = mapOf()
            val mapWithPawns = placePawnsInitialPosition(piecesPositions)
            val mapWithRooks = placeRookCapablancaInitialPosition(mapWithPawns)
            val mapWithKnights = placeKnightCapablancaInitialPosition(mapWithRooks)
            val mapWithBishops = placeBishopCapablancaInitialPosition(mapWithKnights)
            val mapWithQueens = placeQueenCapablancaInitialPosition(mapWithBishops)
            val mapWithKings = placeKingCapablancaInitialPosition(mapWithQueens)
            val mapWithChancellorAndArchbishop = placeArchbishopAndChancellorCapablancaInitialPosition(mapWithKings)
            return ClassicBoard(boardSize, 8, mapWithChancellorAndArchbishop, positions)
        }

        private fun placeRookCapablancaInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val rookMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            rookMap[Position(1, 1)] = PieceFactory.createRook(PieceColor.BLACK)
            rookMap[Position(10, 1)] = PieceFactory.createRook(PieceColor.BLACK)
            rookMap[Position(1, 8)] = PieceFactory.createRook(PieceColor.WHITE)
            rookMap[Position(10, 8)] = PieceFactory.createRook(PieceColor.WHITE)
            return rookMap
        }

        private fun placeKnightCapablancaInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val knightMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            knightMap[Position(2, 1)] = PieceFactory.createKnight(PieceColor.BLACK)
            knightMap[Position(9, 1)] = PieceFactory.createKnight(PieceColor.BLACK)
            knightMap[Position(2, 8)] = PieceFactory.createKnight(PieceColor.WHITE)
            knightMap[Position(9, 8)] = PieceFactory.createKnight(PieceColor.WHITE)
            return knightMap
        }

        private fun placeArchbishopAndChancellorCapablancaInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val archbishopAndChancellorMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            archbishopAndChancellorMap[Position(3, 1)] = PieceFactory.createChancellor(PieceColor.BLACK)
            archbishopAndChancellorMap[Position(8, 1)] = PieceFactory.createArchbishop(PieceColor.BLACK)
            archbishopAndChancellorMap[Position(3, 8)] = PieceFactory.createChancellor(PieceColor.WHITE)
            archbishopAndChancellorMap[Position(8, 8)] = PieceFactory.createArchbishop(PieceColor.WHITE)
            return archbishopAndChancellorMap
        }

        private fun placeQueenCapablancaInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val queenMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            queenMap[Position(6, 1)] = PieceFactory.createQueen(PieceColor.BLACK)
            queenMap[Position(6, 8)] = PieceFactory.createQueen(PieceColor.WHITE)
            return queenMap
        }

        private fun placeKingCapablancaInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val kingMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            kingMap[Position(5, 1)] = PieceFactory.createKing(PieceColor.BLACK)
            kingMap[Position(5, 8)] = PieceFactory.createKing(PieceColor.WHITE)
            return kingMap
        }

        private fun placeBishopCapablancaInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val bishopMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            bishopMap[Position(4, 1)] = PieceFactory.createBishop(PieceColor.BLACK)
            bishopMap[Position(7, 1)] = PieceFactory.createBishop(PieceColor.BLACK)
            bishopMap[Position(4, 8)] = PieceFactory.createBishop(PieceColor.WHITE)
            bishopMap[Position(7, 8)] = PieceFactory.createBishop(PieceColor.WHITE)
            return bishopMap
        }

        private fun fillPositions(size: Int): List<Position> {
            val positionsToReturn: MutableList<Position> = mutableListOf()
            for (i in 1..size) {
                for (j in 1..size) {
                    positionsToReturn.add(Position(i, j))
                }
            }
            return positionsToReturn
        }
    }


}
