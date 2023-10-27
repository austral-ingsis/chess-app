

import edu.austral.dissis.mychess.Position
import edu.austral.dissis.mychess.ReadYaml
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.board.ClassicBoard
import edu.austral.dissis.mychess.factory.PieceFactory
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.piece.PieceColor

class BoardFactory {

    companion object {

        private val classicFileName = "app/src/main/kotlin/edu/austral/dissis/mychess/resources/classic_initial_positions.yml"
        private val capablancaFileName = "app/src/main/kotlin/edu/austral/dissis/mychess/resources/capablanca_initial_positions.yml"

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
            val data = ReadYaml.readYml(classicFileName)
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
            val data = ReadYaml.readYml(classicFileName)
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
            val data = ReadYaml.readYml(classicFileName)
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
            val data = ReadYaml.readYml(classicFileName)
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
            val data = ReadYaml.readYml(classicFileName)
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
            val data = ReadYaml.readYml(classicFileName)
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
            val mapWithPawns = placePawnsCapablancaInitialPosition(piecesPositions)
            val mapWithRooks = placeRookCapablancaInitialPosition(mapWithPawns)
            val mapWithKnights = placeKnightCapablancaInitialPosition(mapWithRooks)
            val mapWithBishops = placeBishopCapablancaInitialPosition(mapWithKnights)
            val mapWithQueens = placeQueenCapablancaInitialPosition(mapWithBishops)
            val mapWithKings = placeKingCapablancaInitialPosition(mapWithQueens)
            val mapWithArchbishop = placeArchbishopCapablancaInitialPosition(mapWithKings)
            val mapWithChancellor = placeChancellorInitialPosition(mapWithArchbishop)
            return ClassicBoard(boardSize, 8, mapWithChancellor, positions)
        }

        private fun placePawnsCapablancaInitialPosition(piecesPositions: Map<Position, Piece>) : Map<Position, Piece>{
            val pawnMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml(capablancaFileName)
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

        private fun placeRookCapablancaInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val rookMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml(capablancaFileName)
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

        private fun placeKnightCapablancaInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val knightMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml(capablancaFileName)
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

        private fun placeArchbishopCapablancaInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val archbishopMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml(capablancaFileName)
            val archbishop = data["archbishops"] as List<Map<String, List<List<Int>>>>
            val blackArchbishopInitialPositions = archbishop[0]["black_archbishop_position"]
            val blackArchbishopInitialPositionsParsed = blackArchbishopInitialPositions?.map { createPositionList(it) }
            for (position in blackArchbishopInitialPositionsParsed!!){
                archbishopMap[position] = PieceFactory.createArchbishop(PieceColor.BLACK)
            }
            val whiteArchbishopInitialPositions = archbishop[1]["white_archbishop_position"]
            val whiteArchbishopInitialPositionsParsed = whiteArchbishopInitialPositions?.map { createPositionList(it) }
            for (position in whiteArchbishopInitialPositionsParsed!!){
                archbishopMap[position] = PieceFactory.createArchbishop(PieceColor.WHITE)
            }
            return archbishopMap
        }

        private fun placeChancellorInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val chancellorMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml(capablancaFileName)
            val chancellor = data["chancellors"] as List<Map<String, List<List<Int>>>>
            val blackChancellorInitialPositions = chancellor[0]["black_chancellor_position"]
            val blackChancellorInitialPositionsParsed = blackChancellorInitialPositions?.map { createPositionList(it) }
            for (position in blackChancellorInitialPositionsParsed!!){
                chancellorMap[position] = PieceFactory.createChancellor(PieceColor.BLACK)
            }
            val whiteChancellorInitialPositions = chancellor[1]["white_chancellor_position"]
            val whiteChancellorInitialPositionsParsed = whiteChancellorInitialPositions?.map { createPositionList(it) }
            for (position in whiteChancellorInitialPositionsParsed!!){
                chancellorMap[position] = PieceFactory.createChancellor(PieceColor.WHITE)
            }
            return chancellorMap
        }

        private fun placeQueenCapablancaInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val queenMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml(capablancaFileName)
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

        private fun placeKingCapablancaInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val kingMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml(capablancaFileName)
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

        private fun placeBishopCapablancaInitialPosition(piecesPositions: Map<Position, Piece>): Map<Position, Piece> {
            val bishopMap: MutableMap<Position, Piece> = piecesPositions.toMutableMap()
            val data = ReadYaml.readYml(capablancaFileName)
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
