package edu.austral.dissis.common

import edu.austral.dissis.checkers.CheckersMovementStrategy
import edu.austral.dissis.checkers.factory.CheckersBoardFactory
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.gameState.GameState
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.GameOver
import edu.austral.dissis.common.turnStrategy.TurnStrategy
import edu.austral.dissis.mychess.ChessMovementStrategy
import edu.austral.dissis.mychess.factory.CapablancaChessBoardFactory
import edu.austral.dissis.mychess.factory.ClassicChessBoardFactory
import java.util.*

class Engine : GameEngine{
    private val sc = Scanner(System.`in`)
    private val movementStrategy = MovementStrategy()
    private val adapter = Adapter()

    override fun init(): InitialState {
        val board = chooseConfiguration()
        val historicalBoards : List<Board> = createHistoryFromBoard(board)
        val turnStrategy = TurnStrategy(PieceColor.WHITE)
        val gameState = GameState(turnStrategy, historicalBoards)
        adapter.saveHistory(gameState)
        return adapter.adaptGameStateToInitialState(gameState)
    }

    override fun applyMove(move: Move): MoveResult {
        val fromPosition = Position(move.from.row, move.from.column)
        val currentBoard = adapter.getLastState().getLastBoard()
        val pieceToMove = currentBoard.getPiecesPositions()[fromPosition]
        val turnStrategy: TurnStrategy = adapter.getLastState().getTurnStrategy()
        val turnManager = getMoveStrategy(getAllNames(currentBoard))
        val toPosition = Position(move.to.row, move.to.column)
        if (pieceToMove == null){
            return InvalidMove("No hay nada en esa posición, intente con otra!")
        }else if (pieceToMove.color != turnStrategy.getCurrentColor()) {
            return InvalidMove("Es el turno del color " + turnStrategy.getCurrentColor().name.lowercase())
        }
        else if (pieceToMove.validator.validateMovement(currentBoard, Movement(pieceToMove, toPosition)) is GameOver){
            val winner = adapter.adaptPieceColorToPlayerColor(turnStrategy.advanceTurn(pieceToMove.color).getCurrentColor())
            return GameOver(winner)
        }
        else {
            val newBoard: Board = movementStrategy.moveTo(
                pieceToMove, toPosition,
                adapter.getLastState().getLastBoard()
            )
            if (newBoard == adapter.getLastState().getLastBoard()) {
                return InvalidMove("Movimiento inválido para " +
                        pieceToMove.id.takeWhile { it.isLetter() })
            }

            val history: List<Board> = createHistoryFromBoard(newBoard)

            val advanceTurn = turnManager.manageTurn(pieceToMove, currentBoard, turnStrategy, newBoard)

            adapter.saveHistory(GameState(advanceTurn, history))

            val chessPieces = adapter.adaptPiecesToChessPieces(newBoard, newBoard.getPiecesPositions().values.toList())
            val currentPlayer = adapter.adaptPieceColorToPlayerColor(advanceTurn.getCurrentColor())

            return NewGameState(chessPieces, currentPlayer)
        }
    }

    private fun createHistoryFromBoard(board: Board) : List<Board>{
        val historialBoards : MutableList<Board> = mutableListOf()
        historialBoards.add(board)
        return historialBoards
    }

    private fun chooseConfiguration() : Board {
        println("Enter the configuration number you want \n • Chess Game \n     1. Classic \n    " +
                " 2. Capablanca \n" +
                " • Checkers Game \n     3. American")
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
