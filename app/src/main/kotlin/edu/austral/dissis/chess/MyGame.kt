package edu.austral.dissis.chess

import chess.program.src.*
import chess.program.src.boardMovement.BoardMovement
import chess.program.src.boardMovement.CastleMovement
import chess.program.src.boardMovement.initialMovement
import chess.program.src.boardValidator.*
import chess.program.src.enums.Color.*;
import chess.program.src.enums.Type
import chess.program.src.enums.Type.*;
import chess.program.src.movement2.DiagonalMovement
import chess.program.src.movement2.HorizontalAndVerticalMovement
import chess.program.src.movement2.JumpMove
import chess.program.src.movement2.Movement2
import chess.program.src.turn.NormalTurn



    fun createInitialChessBoard(): Board {
        val board = Board(HashMap(),8,8)

        // Agregar las piezas de ajedrez en su posición inicial

        board.put(Position(1, 1), PieceImpl(WHITE, FIRSTTOWER, listOf(HorizontalAndVerticalMovement(8, 8,8,8))))
        board.put(Position(1, 2), PieceImpl(WHITE, KNIGHT, listOf(JumpMove(2, 2, 1, 1),JumpMove(1, 1, 2, 2))))
        board.put(Position(1, 3), PieceImpl(WHITE, BISHOP, listOf(DiagonalMovement(8, 8, 8, 8))))
        board.put(Position(1, 4), PieceImpl(WHITE, QUEEN, listOf(DiagonalMovement(8, 8, 8, 8),HorizontalAndVerticalMovement(8, 8,8,8))))
        board.put(Position(1, 5), PieceImpl(WHITE, FRSTKING, listOf(HorizontalAndVerticalMovement(2,2,1,1),DiagonalMovement(1,1,1,1))))
        board.put(Position(1, 6), PieceImpl(WHITE, BISHOP,listOf(DiagonalMovement(8, 8, 8, 8))))
        board.put(Position(1, 7), PieceImpl(WHITE, KNIGHT,listOf(JumpMove(2, 2, 1, 1),JumpMove(1, 1, 2, 2))))
        board.put(Position(1, 8), PieceImpl(WHITE, FIRSTTOWER,listOf(HorizontalAndVerticalMovement(8, 8,8,8))))
        for (i in 1..8) {
            board.put(Position(2, i), PieceImpl(WHITE, FIRSTPAWN, listOf(DiagonalMovement(1, 1, 1, 0),HorizontalAndVerticalMovement(0, 0, 2, 0))))
        }

        // Agregar piezas negras
        board.put(Position(8, 1), PieceImpl(BLACK, FIRSTTOWER,listOf(HorizontalAndVerticalMovement(8, 8,8,8))))
        board.put(Position(8, 2), PieceImpl(BLACK, KNIGHT,listOf(JumpMove(2, 2, 1, 1),JumpMove(1, 1, 2, 2))))
        board.put(Position(8, 3), PieceImpl(BLACK, BISHOP,listOf(DiagonalMovement(8, 8, 8, 8))))
        board.put(Position(8, 4), PieceImpl(BLACK, QUEEN, listOf(DiagonalMovement(8, 8, 8, 8),HorizontalAndVerticalMovement(8, 8,8,8))))
        board.put(Position(8, 5), PieceImpl(BLACK, FRSTKING,listOf(HorizontalAndVerticalMovement(2,2,1,1),DiagonalMovement(1,1,1,1))))
        board.put(Position(8, 6), PieceImpl(BLACK, BISHOP,listOf(DiagonalMovement(8, 8, 8, 8))))
        board.put(Position(8, 7), PieceImpl(BLACK, KNIGHT,listOf(JumpMove(2, 2, 1, 1),JumpMove(1, 1, 2, 2))))
        board.put(Position(8, 8), PieceImpl(BLACK, FIRSTTOWER,listOf(HorizontalAndVerticalMovement(8, 8,8,8))))
        for (i in 1..8) {
            board.put(Position(7, i), PieceImpl(BLACK, FIRSTPAWN,listOf(DiagonalMovement(1, 1, 0, 1),HorizontalAndVerticalMovement(0, 0, 0, 2))))
        }

        // Llenar el resto del tablero con celdas vacías
        for (i in 3..6) {
            for (j in 1..8) {
                board.put(Position(i, j), null)
            }
        }

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

        val movementChange1 = DiagonalMovement(1, 1, 1, 0)
        val movement2 = HorizontalAndVerticalMovement(0, 0, 0, 1)
        val movements2 = ArrayList<Movement2>()
        movements.add(movementChange1)
        movements2.add(movement2)

        val whitePawn = PieceImpl(WHITE, PAWN, movements)
        val blackPawn = PieceImpl(BLACK, PAWN, movements)
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

        val boardMovement = initialMovement(FIRSTPAWN, whitePawn)
        val boardMovement1 = initialMovement(FIRSTPAWN, blackPawn)
        val boardMovement2 = initialMovement(FIRSTTOWER, whiteTower)
        val boardMovement3 = initialMovement(FIRSTTOWER, blackTower)
        val boardMovement4 = initialMovement(FRSTKING, whiteKing)
        val boardMovement5 = initialMovement(FRSTKING, blackKing)
        val boardMovement6 = CastleMovement()
        val boardMovements = ArrayList<BoardMovement>()
        boardMovements.add(boardMovement6)
        boardMovements.add(boardMovement)
        boardMovements.add(boardMovement1)
        boardMovements.add(boardMovement2)
        boardMovements.add(boardMovement3)
        boardMovements.add(boardMovement4)
        boardMovements.add(boardMovement5)

        return boardMovements
    }

    fun createValidators(): List<Validator> {
        val occupiedValidator = OccupiedByTeamPiece()
        val tipos = ArrayList<Type>()
        tipos.add(FIRSTPAWN)
        tipos.add(PAWN)

        val tipos1 = ArrayList<Type>()
        tipos.add(FRSTKING)
        tipos.add(KING)
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
        val gameMode = GameMode(createInitialChessBoard(), createBoardMovements(), createValidators(), NormalTurn())
        return gameMode
    }



