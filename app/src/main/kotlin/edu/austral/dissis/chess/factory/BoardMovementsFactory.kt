package edu.austral.dissis.chess.factory

import checkers.Eat
import common.boardMovement.BoardMovement
import chess.program.src.main.CastleMovement
import common.boardMovement.replacement.InitialReplacementStrategy
import common.boardMovement.replacement.PositionReplacementStrategy
import common.boardMovement.replacement.ReplacementMovement
import common.PieceImpl
import common.Position
import common.enums.Color
import common.enums.Type
import common.movement2.DiagonalMovement
import common.movement2.HorizontalAndVerticalMovement
import common.movement2.Movement2
import common.movement2.strategyMovement.DiagonalObligatoryTrepassin
import common.movement2.strategyMovement.NoEatStrategy

class BoardMovementsFactory {

    fun createCheckersBoardMovements(): List<BoardMovement> {
        val whiteQueen = PieceImpl(Color.WHITE, Type.QUEEN, listOf(DiagonalMovement(2, 2, 2, 2, listOf( DiagonalObligatoryTrepassin(),NoEatStrategy())),DiagonalMovement(1,1,1,1, listOf( NoEatStrategy()))))
        val blackQueen = PieceImpl(Color.BLACK, Type.QUEEN, listOf(DiagonalMovement(2, 2, 2, 2, listOf( DiagonalObligatoryTrepassin(),NoEatStrategy())),DiagonalMovement(1,1,1,1, listOf( NoEatStrategy()))))

        val boardValidator = Eat()
        val boardMovement7 = ReplacementMovement(Type.KNIGHT, whiteQueen, PositionReplacementStrategy(listOf(Position(8, 1), Position(8, 2), Position(8, 3), Position(8, 4), Position(8, 5), Position(8, 6), Position(8, 7), Position(8, 8))))
        val boardMovement8 = ReplacementMovement(Type.KNIGHT, blackQueen, PositionReplacementStrategy(listOf(Position(1, 1), Position(1, 2), Position(1, 3), Position(1, 4), Position(1, 5), Position(1, 6), Position(1, 7), Position(1, 8))))
        //val boardMovement9 = ComposeBoardMovement(listOf(boardValidator,boardMovement7,boardMovement8))
        //return  listOf(boardMovement9)
        return  listOf(boardValidator,boardMovement7,boardMovement8)
    }

    fun createChessBoardMovements(): List<BoardMovement> {
        val movementChange = DiagonalMovement(1, 1, 1, 0)
        val movement = HorizontalAndVerticalMovement(0, 0, 1, 0)
        val movements = ArrayList<Movement2>()
        movements.add(movementChange)
        movements.add(movement)

        val movementChange1 = DiagonalMovement(1, 1, 0, 1)
        val movement2 = HorizontalAndVerticalMovement(0, 0, 0, 1)
        val movements2 = ArrayList<Movement2>()
        movements2.add(movementChange1)
        movements2.add(movement2)

        val whitePawn = PieceImpl(Color.WHITE, Type.PAWN, movements)
        val blackPawn = PieceImpl(Color.BLACK, Type.PAWN, movements2)
        //------------------------
        val movementChange3 = HorizontalAndVerticalMovement(8, 8, 8, 8)
        val movement2List = ArrayList<Movement2>()
        movement2List.add(movementChange3)
        val whiteTower = PieceImpl(Color.WHITE, Type.TOWER, movement2List)
        val blackTower = PieceImpl(Color.BLACK, Type.TOWER, movement2List)
        //------------------------

        val movementChange4 = DiagonalMovement(1, 1, 1, 1)
        val movement3 = HorizontalAndVerticalMovement(1, 1, 1, 1)
        val movement2List1 = ArrayList<Movement2>()
        movement2List1.add(movementChange4)
        movement2List1.add(movement3)
        val whiteKing = PieceImpl(Color.WHITE, Type.KING, movement2List1)
        val blackKing = PieceImpl(Color.BLACK, Type.KING, movement2List1)

        //--------------------reinas
        val whiteQueen = PieceImpl(Color.WHITE, Type.QUEEN, listOf(DiagonalMovement(8, 8, 8, 8), HorizontalAndVerticalMovement(8, 8, 8, 8)))
        val blackQueen = PieceImpl(Color.BLACK, Type.QUEEN, listOf(DiagonalMovement(8, 8, 8, 8), HorizontalAndVerticalMovement(8, 8, 8, 8)))

        val boardMovement = ReplacementMovement(Type.FIRSTPAWN, whitePawn, InitialReplacementStrategy())
        val boardMovement1 = ReplacementMovement(Type.FIRSTPAWN, blackPawn, InitialReplacementStrategy())
        val boardMovement2 = ReplacementMovement(Type.FIRSTTOWER, whiteTower, InitialReplacementStrategy())
        val boardMovement3 = ReplacementMovement(Type.FIRSTTOWER, blackTower, InitialReplacementStrategy())
        val boardMovement4 = ReplacementMovement(Type.FRSTKING, whiteKing, InitialReplacementStrategy())
        val boardMovement5 = ReplacementMovement(Type.FRSTKING, blackKing, InitialReplacementStrategy())
        val boardMovement6 = CastleMovement()
        val boardMovement7 = ReplacementMovement(Type.PAWN, whiteQueen, PositionReplacementStrategy(listOf(Position(8, 1), Position(8, 2), Position(8, 3), Position(8, 4), Position(8, 5), Position(8, 6), Position(8, 7), Position(8, 8))))
        val boardMovement8 = ReplacementMovement(Type.PAWN, blackQueen, PositionReplacementStrategy(listOf(Position(1, 1), Position(1, 2), Position(1, 3), Position(1, 4), Position(1, 5), Position(1, 6), Position(1, 7), Position(1, 8))))
        val boardMovements = ArrayList<BoardMovement>()
        boardMovements.add(boardMovement6)
        boardMovements.add(boardMovement)
        boardMovements.add(boardMovement1)
        boardMovements.add(boardMovement2)
        boardMovements.add(boardMovement3)
        boardMovements.add(boardMovement4)
        boardMovements.add(boardMovement5)
        boardMovements.add(boardMovement7)
        boardMovements.add(boardMovement8)

        return boardMovements
    }

}