package edu.austral.dissis.chess

import chess.program.src.boardMovement.BoardMovement
import chess.program.src.boardMovement.CastleMovement
import chess.program.src.boardMovement.replacement.InitialReplacementStrategy
import chess.program.src.boardMovement.replacement.PositionReplacementStrategy
import chess.program.src.boardMovement.replacement.ReplacementMovement
import chess.program.src.boardValidator.*
import common.enums.Color.*;
import common.enums.Type
import common.enums.Type.*;
import common.movement2.DiagonalMovement
import common.movement2.HorizontalAndVerticalMovement
import common.movement2.JumpMove
import common.movement2.Movement2
import common.*
import common.turn.NormalTurn
import common.winningCondition.CheckMate


fun createInitialChessBoard(): Board {
        val board = Board(HashMap(), 8, 8)

        // Agregar las piezas de ajedrez en su posición inicial

        board.put(Position(1, 1), PieceImpl(WHITE, FIRSTTOWER, listOf(HorizontalAndVerticalMovement(8, 8, 8, 8))))
        board.put(Position(1, 2), PieceImpl(WHITE, KNIGHT, listOf(JumpMove(2, 2, 1, 1), JumpMove(1, 1, 2, 2))))
        board.put(Position(1, 3), PieceImpl(WHITE, BISHOP, listOf(DiagonalMovement(8, 8, 8, 8))))
        board.put(Position(1, 4), PieceImpl(WHITE, QUEEN, listOf(DiagonalMovement(8, 8, 8, 8), HorizontalAndVerticalMovement(8, 8, 8, 8))))
        board.put(Position(1, 5), PieceImpl(WHITE, FRSTKING, listOf(HorizontalAndVerticalMovement(2, 2, 1, 1), DiagonalMovement(1, 1, 1, 1))))
        board.put(Position(1, 6), PieceImpl(WHITE, BISHOP, listOf(DiagonalMovement(8, 8, 8, 8))))
        board.put(Position(1, 7), PieceImpl(WHITE, KNIGHT, listOf(JumpMove(2, 2, 1, 1), JumpMove(1, 1, 2, 2))))
        board.put(Position(1, 8), PieceImpl(WHITE, FIRSTTOWER, listOf(HorizontalAndVerticalMovement(8, 8, 8, 8))))
        for (i in 1..8) {
            board.put(Position(2, i), PieceImpl(WHITE, FIRSTPAWN, listOf(DiagonalMovement(1, 1, 1, 0), HorizontalAndVerticalMovement(0, 0, 2, 0))))
        }

        // Agregar piezas negras
        board.put(Position(8, 1), PieceImpl(BLACK, FIRSTTOWER, listOf(HorizontalAndVerticalMovement(8, 8, 8, 8))))
        board.put(Position(8, 2), PieceImpl(BLACK, KNIGHT, listOf(JumpMove(2, 2, 1, 1), JumpMove(1, 1, 2, 2))))
        board.put(Position(8, 3), PieceImpl(BLACK, BISHOP, listOf(DiagonalMovement(8, 8, 8, 8))))
        board.put(Position(8, 4), PieceImpl(BLACK, QUEEN, listOf(DiagonalMovement(8, 8, 8, 8), HorizontalAndVerticalMovement(8, 8, 8, 8))))
        board.put(Position(8, 5), PieceImpl(BLACK, FRSTKING, listOf(HorizontalAndVerticalMovement(2, 2, 1, 1), DiagonalMovement(1, 1, 1, 1))))
        board.put(Position(8, 6), PieceImpl(BLACK, BISHOP, listOf(DiagonalMovement(8, 8, 8, 8))))
        board.put(Position(8, 7), PieceImpl(BLACK, KNIGHT, listOf(JumpMove(2, 2, 1, 1), JumpMove(1, 1, 2, 2))))
        board.put(Position(8, 8), PieceImpl(BLACK, FIRSTTOWER, listOf(HorizontalAndVerticalMovement(8, 8, 8, 8))))
        for (i in 1..8) {
            board.put(Position(7, i), PieceImpl(BLACK, FIRSTPAWN, listOf(DiagonalMovement(1, 1, 0, 1), HorizontalAndVerticalMovement(0, 0, 0, 2))))
        }

        // Llenar el resto del tablero con celdas vacías


        return board
    }

    fun createTestChessBoard(): Board {
        val board = Board(HashMap(), 4, 4)
        board.put(Position(1, 1), PieceImpl(WHITE, FRSTKING, listOf(HorizontalAndVerticalMovement(2, 2, 1, 1), DiagonalMovement(1, 1, 1, 1))))
        board.put(Position(4, 2), PieceImpl(BLACK, QUEEN, listOf(DiagonalMovement(8, 8, 8, 8), HorizontalAndVerticalMovement(8, 8, 8, 8))))
        board.put(Position(3, 4), PieceImpl(BLACK, QUEEN, listOf(DiagonalMovement(8, 8, 8, 8), HorizontalAndVerticalMovement(8, 8, 8, 8))))
        board.put(Position(4, 4), PieceImpl(BLACK, FRSTKING, listOf(HorizontalAndVerticalMovement(2, 2, 1, 1), DiagonalMovement(1, 1, 1, 1))))
        return board
    }


    fun createGame(gm: GameMode): Game {
        val player1 = Player("player1", WHITE)
        val player2 = Player("player2", BLACK)
        val players = ArrayList<Player>()
        players.add(player1)
        players.add(player2)
        val game = Game(gm, players)
        return game
    }


    fun createBoardMovements(): List<BoardMovement> {
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

        val whitePawn = PieceImpl(WHITE, PAWN, movements)
        val blackPawn = PieceImpl(BLACK, PAWN, movements2)
        //------------------------
        val movementChange3 = HorizontalAndVerticalMovement(8, 8, 8, 8)
        val movement2List = ArrayList<Movement2>()
        movement2List.add(movementChange3)
        val whiteTower = PieceImpl(WHITE, TOWER, movement2List)
        val blackTower = PieceImpl(BLACK, TOWER, movement2List)
        //------------------------

        val movementChange4 = DiagonalMovement(1, 1, 1, 1)
        val movement3 = HorizontalAndVerticalMovement(1, 1, 1, 1)
        val movement2List1 = ArrayList<Movement2>()
        movement2List1.add(movementChange4)
        movement2List1.add(movement3)
        val whiteKing = PieceImpl(WHITE, KING, movement2List1)
        val blackKing = PieceImpl(BLACK, KING, movement2List1)

        //--------------------reinas
        val whiteQueen = PieceImpl(WHITE, QUEEN, listOf(DiagonalMovement(8, 8, 8, 8), HorizontalAndVerticalMovement(8, 8, 8, 8)))
        val blackQueen = PieceImpl(BLACK, QUEEN, listOf(DiagonalMovement(8, 8, 8, 8), HorizontalAndVerticalMovement(8, 8, 8, 8)))

        val boardMovement = ReplacementMovement(FIRSTPAWN, whitePawn,InitialReplacementStrategy())
        val boardMovement1 = ReplacementMovement(FIRSTPAWN, blackPawn,InitialReplacementStrategy())
        val boardMovement2 = ReplacementMovement(FIRSTTOWER, whiteTower,InitialReplacementStrategy())
        val boardMovement3 = ReplacementMovement(FIRSTTOWER, blackTower,InitialReplacementStrategy())
        val boardMovement4 = ReplacementMovement(FRSTKING, whiteKing,InitialReplacementStrategy())
        val boardMovement5 = ReplacementMovement(FRSTKING, blackKing,InitialReplacementStrategy())
        val boardMovement6 = CastleMovement()
        val boardMovement7 = ReplacementMovement(PAWN, whiteQueen,PositionReplacementStrategy(listOf(Position(8, 1), Position(8, 2), Position(8, 3), Position(8, 4), Position(8, 5), Position(8, 6), Position(8, 7), Position(8, 8))))
        val boardMovement8 = ReplacementMovement(PAWN, blackQueen, PositionReplacementStrategy(listOf(Position(1, 1), Position(1, 2), Position(1, 3), Position(1, 4), Position(1, 5), Position(1, 6), Position(1, 7), Position(1, 8))))
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

    fun createValidators(): List<Validator> {
        val occupiedValidator = OccupiedByTeamPiece()
        val tipos = ArrayList<Type>()
        tipos.add(FIRSTPAWN)
        tipos.add(PAWN)

        val tipos1 = ArrayList<Type>()
        tipos1.add(FRSTKING)
        tipos1.add(KING)
        val cantBeEaten = cantBeEaten(tipos1)

        val moveIfEat = MoveIfEat(tipos, DiagonalMovement(1, 1, 1, 1))
        val moveIfNotEat = MoveIfNotEat(tipos, HorizontalAndVerticalMovement(1, 1, 1, 1))
        val checkValidator = CheckValidator()
        val boardValidators = ArrayList<Validator>()
        boardValidators.add(checkValidator)
        boardValidators.add(moveIfEat)
        boardValidators.add(moveIfNotEat)
        boardValidators.add(occupiedValidator)
        boardValidators.add(cantBeEaten)

        return boardValidators
    }

    fun createGameMode(): GameMode {
        val gameMode = GameMode(createInitialChessBoard(), createBoardMovements(), createValidators(), NormalTurn(), CheckMate())
        return gameMode
    }



