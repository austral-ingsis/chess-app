package edu.austral.dissis.checkers

import edu.austral.dissis.checkers.factory.CheckersBoardFactory
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.gameState.GameState
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.turnStrategy.ClassicTurnStrategy
import edu.austral.dissis.common.turnStrategy.TurnStrategy
import edu.austral.dissis.common.Adapter
import edu.austral.dissis.mychess.MovementStrategy
import edu.austral.dissis.common.Position


class CheckersEngine : GameEngine {

    private val adapter = Adapter()
    private val movementStrategy = MovementStrategy()

    override fun init(): InitialState {
        val board = CheckersBoardFactory.createInitialClassicCheckersBoard()
        val historicalBoards = createHistoryFromBoard(board)
        val turnStrategy : TurnStrategy = ClassicTurnStrategy(PieceColor.WHITE)
        val gameState = GameState(turnStrategy, historicalBoards)
        adapter.saveHistory(gameState)
        return adapter.adaptGameStateToInitialState(gameState)
    }

    override fun applyMove(move: Move): MoveResult {
        val fromPosition = Position(move.from.row, move.from.column)
        val currentBoard = adapter.getLastState().getLastBoard()
        val pieceToMove = currentBoard.getPiecesPositions()[fromPosition]
        val turnStrategy: TurnStrategy = adapter.getLastState().getTurnStrategy()
        val toPosition = Position(move.to.row, move.to.column)
        if (pieceToMove == null) {
            return InvalidMove("No hay nada en esa posición, intente con otra posición!")
        } else if (pieceToMove.color != turnStrategy.getCurrentColor()) {
            return InvalidMove("Es el turno del color " + turnStrategy.getCurrentColor().name.lowercase())
        } else {
            val newBoard: Board = movementStrategy.moveTo(
                pieceToMove, toPosition,
                adapter.getLastState().getLastBoard()
            )
            if (newBoard == adapter.getLastState().getLastBoard()) {
                return InvalidMove("Movimiento inválido para " + pieceToMove.id.takeWhile { it.isLetter() })
            }

            val history: List<Board> = createHistoryFromBoard(newBoard)
            val advanceTurn = turnStrategy.advanceTurn(pieceToMove.color)
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
}