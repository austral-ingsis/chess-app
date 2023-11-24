package edu.austral.dissis.common

import edu.austral.dissis.checkers.CheckersMovementStrategy
import edu.austral.dissis.checkers.factory.CheckersBoardFactory
import edu.austral.dissis.checkers.factory.CheckersPieceFactory
import edu.austral.dissis.checkers.CheckersWinCondition
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.gameState.GameState
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.turnStrategy.TurnStrategy
import edu.austral.dissis.mychess.ChessMovementStrategy
import edu.austral.dissis.mychess.factory.CapablancaChessBoardFactory
import edu.austral.dissis.mychess.factory.ChessPieceFactory
import edu.austral.dissis.mychess.factory.ClassicChessBoardFactory
import edu.austral.dissis.mychess.ChessWinCondition
import java.util.*

class Engine : GameEngine{
    private val sc = Scanner(System.`in`)
    private val adapter = Adapter()

    override fun init(): InitialState {
        val board = chooseConfiguration()
        val historicalBoards : List<Board> = createHistoryFromBoard(board)
        val turnStrategy = TurnStrategy(PieceColor.WHITE)
        val winCondition = getWinningCondition(getAllNames(board))
        val gameState = GameState(turnStrategy, historicalBoards,
            //winCondition
        )
        adapter.saveHistory(gameState)
        return adapter.adaptGameStateToInitialState(gameState)
    }

    override fun applyMove(move: Move): MoveResult {
        val fromPosition = Position(move.from.row, move.from.column)
        val toPosition = Position(move.to.row, move.to.column)
        val currentBoard = adapter.getLastState().getLastBoard()
        val movementStrategy = MovementStrategy(getPieceFactory(getAllNames(currentBoard)))
        val pieceToMove = currentBoard.getPiecesPositions()[fromPosition]
        val turnStrategy: TurnStrategy = adapter.getLastState().getTurnStrategy()
        val turnManager = getMoveStrategy(getAllNames(currentBoard))

        return when {
            pieceToMove == null -> InvalidMove("That position is empty, try another one!")
            pieceToMove.color != turnStrategy.getCurrentColor() ->
                InvalidMove("It's the ${turnStrategy.getCurrentColor().name} team's turn.")
            else -> processValidMove(fromPosition, toPosition, currentBoard, movementStrategy, turnStrategy, turnManager)
        }


    }

    private fun processValidMove(
        fromPosition: Position,
        toPosition: Position,
        currentBoard: Board,
        movementStrategy: MovementStrategy,
        turnStrategy: TurnStrategy,
        turnManager: ManageTurns
    ): MoveResult {
        val newBoard: Board = movementStrategy.moveTo(fromPosition, toPosition, currentBoard)
        val pieceToMove = currentBoard.getPiece(fromPosition)

        /*val winConditionResult = adapter.getLastState().getWinCondition().validateMovement(newBoard, Movement(fromPosition, toPosition))
        if (winConditionResult is GameOver || winConditionResult is InvalidMove) {
            return winConditionResult
        }*/

        val turn = turnManager.manageTurn(pieceToMove!!, currentBoard, turnStrategy, newBoard)
        if (newBoard == adapter.getLastState().getLastBoard()) {
            return InvalidMove("Invalid move for ${pieceToMove.pieceType}")
        }
        if (turn == turnStrategy){
            return InvalidMove("Look at the board again!")
        }

        val history: List<Board> = createHistoryFromBoard(newBoard)
        adapter.saveHistory(GameState(turn, history,
            //adapter.getLastState().getWinCondition()
        ))

        val chessPieces = adapter.adaptPiecesToChessPieces(newBoard, newBoard.getPiecesPositions().values.toList())
        val currentPlayer = adapter.adaptPieceColorToPlayerColor(turn.getCurrentColor())

        return NewGameState(chessPieces, currentPlayer)
    }

    private fun createHistoryFromBoard(board: Board) : List<Board>{
        val historialBoards : MutableList<Board> = mutableListOf()
        historialBoards.add(board)
        return historialBoards
    }

    private fun chooseConfiguration() : Board {
        println("Enter the configuration number you want \n • Chess Game \n     1. Classic chess \n    " +
                " 2. Capablanca \n" +
                " • Checkers Game \n     3. Classic checkers")
        val option: Int = sc.nextInt()
        val factory = chooseBoardFactory(option)
        return factory.createInitialBoard()
    }

    private fun chooseBoardFactory(option: Int): BoardFactory {
        return when (option) {
            1 -> {
                ClassicChessBoardFactory()
            }
            2 -> {
                CapablancaChessBoardFactory()
            }
            else -> {
                CheckersBoardFactory()
            }
        }
    }

    private fun getMoveStrategy(piecesNames: List<String>): ManageTurns{
        return if (piecesNames.contains("king")){
            ChessMovementStrategy()
        }else{
            CheckersMovementStrategy()
        }
    }

    private fun getPieceFactory(piecesNames: List<String>): PieceFactory{
        return if (piecesNames.contains("king")){
            ChessPieceFactory()
        }else{
            CheckersPieceFactory()
        }
    }

    private fun getWinningCondition(piecesNames: List<String>): WinCondition{
        return if (piecesNames.contains("king")){
            ChessWinCondition()
        }else{
            CheckersWinCondition()
        }
    }

    private fun getAllNames(board: Board) : List<String>{
        val pieces = board.getPiecesPositions().values
        val names = mutableListOf<String>()
        for (piece in pieces){
            val name = piece.id.takeWhile { it.isLetter() }
            names.add(name)
        }
        return names.toList()
    }
}
