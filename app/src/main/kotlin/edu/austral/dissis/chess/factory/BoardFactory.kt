package edu.austral.dissis.chess.factory

import common.Board
import common.PieceImpl
import common.Position
import common.enums.Color
import common.enums.Type
import common.movement2.DiagonalMovement
import common.movement2.HorizontalAndVerticalMovement
import common.movement2.JumpMove
import common.movement2.strategyMovement.DiagonalObligatoryTrepassin
import common.movement2.strategyMovement.NoEatStrategy

class BoardFactory {


    fun createCheckersInitialChessBoard(): Board {
        val board = Board(HashMap(), 8, 8)
        for (fila in 1 .. 8) {
            for (columna in 1 .. 8) {
                if (columna % 2 != 0) {
                    if (fila == 1 || fila == 3) {
                        board.put(Position(fila, columna), PieceImpl(Color.WHITE, Type.KNIGHT, listOf(DiagonalMovement(2, 2, 2, 0, listOf( DiagonalObligatoryTrepassin(), NoEatStrategy())), DiagonalMovement(1,1,1,0, listOf( NoEatStrategy())))))
                    }
                    if (fila == 7 ) {
                        board.put(Position(fila, columna), PieceImpl(Color.BLACK, Type.KNIGHT, listOf(DiagonalMovement(2, 2, 0, 2, listOf( DiagonalObligatoryTrepassin(), NoEatStrategy())), DiagonalMovement(1,1,0,1, listOf( NoEatStrategy())))))
                    }
                }

                else {
                    if (fila == 2) {
                        board.put(Position(fila, columna), PieceImpl(Color.WHITE, Type.KNIGHT, listOf(DiagonalMovement(2, 2, 2, 0, listOf( DiagonalObligatoryTrepassin(), NoEatStrategy())), DiagonalMovement(1,1,1,0, listOf( NoEatStrategy())))))
                    }
                    if (fila == 6 || fila == 8) {
                        board.put(Position(fila, columna), PieceImpl(Color.BLACK, Type.KNIGHT, listOf(DiagonalMovement(2, 2, 0, 2, listOf( DiagonalObligatoryTrepassin(), NoEatStrategy())), DiagonalMovement(1,1,0,1, listOf( NoEatStrategy())))))
                    }
                }
            }

        }
        return board;
    }

    fun createInitialChessBoard(): Board {
        val board = Board(HashMap(), 8, 8)

        // Agregar las piezas de ajedrez en su posición inicial

        board.put(Position(1, 1), PieceImpl(Color.WHITE, Type.FIRSTTOWER, listOf(HorizontalAndVerticalMovement(8, 8, 8, 8))))
        board.put(Position(1, 2), PieceImpl(Color.WHITE, Type.KNIGHT, listOf(JumpMove(2, 2, 1, 1), JumpMove(1, 1, 2, 2))))
        board.put(Position(1, 3), PieceImpl(Color.WHITE, Type.BISHOP, listOf(DiagonalMovement(8, 8, 8, 8))))
        board.put(Position(1, 4), PieceImpl(Color.WHITE, Type.QUEEN, listOf(DiagonalMovement(8, 8, 8, 8), HorizontalAndVerticalMovement(8, 8, 8, 8))))
        board.put(Position(1, 5), PieceImpl(Color.WHITE, Type.FRSTKING, listOf(HorizontalAndVerticalMovement(2, 2, 1, 1), DiagonalMovement(1, 1, 1, 1))))
        board.put(Position(1, 6), PieceImpl(Color.WHITE, Type.BISHOP, listOf(DiagonalMovement(8, 8, 8, 8))))
        board.put(Position(1, 7), PieceImpl(Color.WHITE, Type.KNIGHT, listOf(JumpMove(2, 2, 1, 1), JumpMove(1, 1, 2, 2))))
        board.put(Position(1, 8), PieceImpl(Color.WHITE, Type.FIRSTTOWER, listOf(HorizontalAndVerticalMovement(8, 8, 8, 8))))
        for (i in 1..8) {
            board.put(Position(2, i), PieceImpl(Color.WHITE, Type.FIRSTPAWN, listOf(DiagonalMovement(1, 1, 1, 0), HorizontalAndVerticalMovement(0, 0, 2, 0))))
        }

        // Agregar piezas negras
        board.put(Position(8, 1), PieceImpl(Color.BLACK, Type.FIRSTTOWER, listOf(HorizontalAndVerticalMovement(8, 8, 8, 8))))
        board.put(Position(8, 2), PieceImpl(Color.BLACK, Type.KNIGHT, listOf(JumpMove(2, 2, 1, 1), JumpMove(1, 1, 2, 2))))
        board.put(Position(8, 3), PieceImpl(Color.BLACK, Type.BISHOP, listOf(DiagonalMovement(8, 8, 8, 8))))
        board.put(Position(8, 4), PieceImpl(Color.BLACK, Type.QUEEN, listOf(DiagonalMovement(8, 8, 8, 8), HorizontalAndVerticalMovement(8, 8, 8, 8))))
        board.put(Position(8, 5), PieceImpl(Color.BLACK, Type.FRSTKING, listOf(HorizontalAndVerticalMovement(2, 2, 1, 1), DiagonalMovement(1, 1, 1, 1))))
        board.put(Position(8, 6), PieceImpl(Color.BLACK, Type.BISHOP, listOf(DiagonalMovement(8, 8, 8, 8))))
        board.put(Position(8, 7), PieceImpl(Color.BLACK, Type.KNIGHT, listOf(JumpMove(2, 2, 1, 1), JumpMove(1, 1, 2, 2))))
        board.put(Position(8, 8), PieceImpl(Color.BLACK, Type.FIRSTTOWER, listOf(HorizontalAndVerticalMovement(8, 8, 8, 8))))
        for (i in 1..8) {
            board.put(Position(7, i), PieceImpl(Color.BLACK, Type.FIRSTPAWN, listOf(DiagonalMovement(1, 1, 0, 1), HorizontalAndVerticalMovement(0, 0, 0, 2))))
        }

        // Llenar el resto del tablero con celdas vacías


        return board
    }

    fun createTestChessBoard(): Board {
        val board = Board(HashMap(), 4, 4)
        board.put(Position(1, 1), PieceImpl(Color.WHITE, Type.FRSTKING, listOf(HorizontalAndVerticalMovement(2, 2, 1, 1), DiagonalMovement(1, 1, 1, 1))))
        board.put(Position(4, 2), PieceImpl(Color.BLACK, Type.QUEEN, listOf(DiagonalMovement(8, 8, 8, 8), HorizontalAndVerticalMovement(8, 8, 8, 8))))
        board.put(Position(3, 4), PieceImpl(Color.BLACK, Type.QUEEN, listOf(DiagonalMovement(8, 8, 8, 8), HorizontalAndVerticalMovement(8, 8, 8, 8))))
        board.put(Position(4, 4), PieceImpl(Color.BLACK, Type.FRSTKING, listOf(HorizontalAndVerticalMovement(2, 2, 1, 1), DiagonalMovement(1, 1, 1, 1))))
        return board
    }

}