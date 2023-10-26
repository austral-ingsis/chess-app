package edu.austral.dissis.chess

import checkers.CheckersWin
import checkers.Eat
import checkers.PriorityMovement
import chess.program.src.boardMovement.BoardMovement
import chess.program.src.boardMovement.replacement.PositionReplacementStrategy
import chess.program.src.boardMovement.replacement.ReplacementMovement
import chess.program.src.boardValidator.*
import common.Game
import common.*
import common.enums.Color
import common.enums.Type
import common.movement2.DiagonalMovement
import common.movement2.strategyMovement.DiagonalObligatoryTrepassin
import common.movement2.strategyMovement.NoEatStrategy
import checkers.CheckersTurn


fun createCheckersInitialChessBoard(): Board {
        val board = Board(HashMap(), 8, 8)
        for (fila in 1 .. 8) {
            for (columna in 1 .. 8) {
                if (columna % 2 != 0) {
                    if (fila == 1 || fila == 3) {
                        board.put(Position(fila, columna), PieceImpl(Color.WHITE, Type.KNIGHT, listOf(DiagonalMovement(2, 2, 2, 0, listOf( DiagonalObligatoryTrepassin(),NoEatStrategy())),DiagonalMovement(1,1,1,0, listOf( NoEatStrategy())))))
                    }
                    if (fila == 7 ) {
                        board.put(Position(fila, columna), PieceImpl(Color.BLACK, Type.KNIGHT, listOf(DiagonalMovement(2, 2, 0, 2, listOf( DiagonalObligatoryTrepassin(),NoEatStrategy())),DiagonalMovement(1,1,0,1, listOf( NoEatStrategy())))))
                    }
                }

                else {
                        if (fila == 2) {
                            board.put(Position(fila, columna), PieceImpl(Color.WHITE, Type.KNIGHT, listOf(DiagonalMovement(2, 2, 2, 0, listOf( DiagonalObligatoryTrepassin(),NoEatStrategy())),DiagonalMovement(1,1,1,0, listOf( NoEatStrategy())))))
                        }
                        if (fila == 6 || fila == 8) {
                            board.put(Position(fila, columna), PieceImpl(Color.BLACK, Type.KNIGHT, listOf(DiagonalMovement(2, 2, 0, 2, listOf( DiagonalObligatoryTrepassin(),NoEatStrategy())),DiagonalMovement(1,1,0,1, listOf( NoEatStrategy())))))
                        }
                    }
                }

        }
        return board;
    }


    fun createCheckersGame(gm: GameMode): Game {
        val player1 = Player("player1", Color.WHITE)
        val player2 = Player("player2", Color.BLACK)
        val players = ArrayList<Player>()
        players.add(player1)
        players.add(player2)
        val game = Game(gm, players)
        return game
    }


    fun createCheckersValidators(): List<Validator> {
            val priorityMovement = PriorityMovement(DiagonalMovement(2,2,2,0, listOf( DiagonalObligatoryTrepassin(),NoEatStrategy())),Color.WHITE)
            val priorityMovement1 = PriorityMovement(DiagonalMovement(2,2,0,2,listOf( DiagonalObligatoryTrepassin(),NoEatStrategy())),Color.BLACK)
            val boardValidators = ArrayList<Validator>()
            boardValidators.add(priorityMovement)
            boardValidators.add(priorityMovement1)

            return boardValidators
        }

    fun createCheckersBoardMovements(): List<BoardMovement> {
        val whiteQueen = PieceImpl(Color.WHITE, Type.QUEEN, listOf(DiagonalMovement(2, 2, 2, 2, listOf( DiagonalObligatoryTrepassin(),NoEatStrategy())),DiagonalMovement(1,1,1,1, listOf( NoEatStrategy()))))
        val blackQueen = PieceImpl(Color.BLACK, Type.QUEEN, listOf(DiagonalMovement(2, 2, 2, 2, listOf( DiagonalObligatoryTrepassin(),NoEatStrategy())),DiagonalMovement(1,1,1,1, listOf( NoEatStrategy()))))

        val boardValidator = Eat()
        val boardMovement7 = ReplacementMovement(Type.KNIGHT, whiteQueen,PositionReplacementStrategy(listOf(Position(8, 1), Position(8, 2), Position(8, 3), Position(8, 4), Position(8, 5), Position(8, 6), Position(8, 7), Position(8, 8))))
        val boardMovement8 = ReplacementMovement(Type.KNIGHT, blackQueen, PositionReplacementStrategy(listOf(Position(1, 1), Position(1, 2), Position(1, 3), Position(1, 4), Position(1, 5), Position(1, 6), Position(1, 7), Position(1, 8))))
        return  listOf(boardValidator,boardMovement7,boardMovement8) //listOf(boadMovement9)
    }

    fun createCheckersGameMode(): GameMode {
        val board = createCheckersInitialChessBoard();
        val turn = CheckersTurn(board)
        val gameMode = GameMode(board, createCheckersBoardMovements(), createCheckersValidators(), turn, CheckersWin())
        return gameMode
    }




