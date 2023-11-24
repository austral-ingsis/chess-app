package edu.austral.dissis.common

import edu.austral.dissis.checkers.CheckersMovementStrategy
import edu.austral.dissis.checkers.factory.CheckersBoardFactory
import edu.austral.dissis.checkers.factory.CheckersPieceFactory
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.gameState.GameState
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.InitialStateResult
import edu.austral.dissis.common.result.Result
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.turnStrategy.TurnStrategy
import edu.austral.dissis.mychess.ChessMovementStrategy
import edu.austral.dissis.mychess.factory.CapablancaChessBoardFactory
import edu.austral.dissis.mychess.factory.ChessPieceFactory
import edu.austral.dissis.mychess.factory.ClassicChessBoardFactory
import java.util.*

class Game {
    private val adapter = Adapter()
    private val sc = Scanner(System.`in`)

    fun init(): InitialStateResult {
        val initialBoard = chooseConfiguration()
        val turnStrategy = TurnStrategy(PieceColor.WHITE)
        adapter.saveHistory(GameState(turnStrategy, listOf(initialBoard)))
        return InitialStateResult(turnStrategy, initialBoard)
    }

    fun applyMove(movement: Movement): Result{
        val currentBoard = adapter.getLastState().getLastBoard()
        val movementStrategy = MovementStrategy(getPieceFactory(currentBoard))
        val pieceToMove = currentBoard.getPiece(movement.from)
        val currentTurn = adapter.getLastState().getTurnStrategy()
        //val turnManager = getMoveStrategy(currentBoard)
        if (pieceToMove == null){
            return FailureResult("That position is empty, try another one!")
        }
        val newBoard = movementStrategy.moveTo(movement, currentBoard)
        //val captureTurn = turnManager.manageTurn(pieceToMove!!, currentBoard, currentTurn, newBoard)
        if (newBoard == adapter.getLastState().getLastBoard()){
            return FailureResult("Invalid move for ${pieceToMove?.pieceType}")
        }

        if (pieceToMove.color != currentTurn.getCurrentColor()){
            return FailureResult("It's the ${currentTurn.getCurrentColor().name} team's turn.")
        }

        val advancedTurn = currentTurn.advanceTurn(pieceToMove.color)
        val history = createHistoryFromBoard(newBoard)
        adapter.saveHistory(GameState(advancedTurn, history))

        return SuccessfulResult(newBoard, newBoard.getPiecesPositions().values.toList(), advancedTurn.getCurrentColor())

    }

    private fun chooseConfiguration() : Board {
        println("Enter the configuration number you want \n • Chess Game \n     1. Classic chess \n    " +
                " 2. Capablanca \n" +
                " • Checkers Game \n     3. Classic checkers")
        val option: Int = sc.nextInt()
        val factory = chooseBoardFactory(option)
        return factory.createInitialBoard()
    }

    private fun createHistoryFromBoard(board: Board) : List<Board>{
        val historialBoards : MutableList<Board> = mutableListOf()
        historialBoards.add(board)
        return historialBoards
    }

    private fun getMoveStrategy(board: Board): ManageTurns{
        val piecesNames = getAllNames(board)
        return if (piecesNames.contains("king")){
            ChessMovementStrategy()
        }else{
            CheckersMovementStrategy()
        }
    }

    private fun getPieceFactory(board: Board): PieceFactory{
        val piecesNames = getAllNames(board)
        return if (piecesNames.contains("king")){
            ChessPieceFactory()
        }else{
            CheckersPieceFactory()
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

}