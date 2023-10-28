package edu.austral.dissis.mychess

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.factory.BoardFactory
import edu.austral.dissis.mychess.gameState.GameState
import edu.austral.dissis.mychess.piece.PieceColor
import edu.austral.dissis.mychess.turnStrategy.ClassicTurnStrategy
import edu.austral.dissis.mychess.turnStrategy.TurnStrategy
import edu.austral.dissis.mychess.validator.specificValidators.KingInCheckValidator
import java.lang.RuntimeException
import java.util.*

class MyEngine : GameEngine {

    private val sc = Scanner(System.`in`)
    private val movementStrategy = MovementStrategy()
    private val adapter = Adapter()

    override fun init(): InitialState {
        val board = chooseConfiguration()
        val historicalBoards : List<Board> = createHistoryFromBoard(board)
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
        if (pieceToMove == null){
            return InvalidMove("No hay nada en esa posición, intente con otra posición!")
        }else if (pieceToMove.color != turnStrategy.getCurrentColor()) {
            return InvalidMove("Es el turno del color " + turnStrategy.getCurrentColor().name.lowercase())
        } else {
            val newBoard: Board = movementStrategy.moveTo(
                pieceToMove, toPosition,
                adapter.getLastState().getLastBoard()
            )
            if (newBoard == adapter.getLastState().getLastBoard()) {
                return InvalidMove("Movimiento inválido para " +
                        pieceToMove.id.takeWhile { it.isLetter() })
            }
            val kingColor = pieceToMove.color
            val kingPosition: Position = findKingPosition(newBoard, kingColor)

            // Verificar si el rey del jugador actual está en jaque en el nuevo tablero
            val kingInCheckValidator = KingInCheckValidator()
//            val checkMateValidator = CheckMateValidator()
            if (kingInCheckValidator.isKingInCheck(newBoard, kingPosition, kingColor)) {
                return InvalidMove("Tu rey está en jaque")
            }

            val history: List<Board> = createHistoryFromBoard(newBoard)
            val advanceTurn = turnStrategy.advanceTurn(pieceToMove.color)
            adapter.saveHistory(GameState(advanceTurn, history))

            // Verificar si el jugador actual está en jaque mate
//            if (checkMateValidator.validateMovement(history.last(), kingPosition, kingColor, movement)){
//                return GameOver("Jaque Mate!")
//            }

            val chessPieces = adapter.adaptPiecesToChessPieces(newBoard, newBoard.getPiecesPositions().values.toList())
            val currentPlayer = adapter.adaptPieceColorToPlayerColor(advanceTurn.getCurrentColor())

            return NewGameState(chessPieces, currentPlayer)
        }
    }

    private fun findKingPosition(board: Board, kingColor: PieceColor) : Position{
        for (piece in board.getPiecesPositions().values){
            val pieceName : String = piece.id.takeWhile { it.isLetter() }
            if (pieceName == "king" && piece.color == kingColor){
                return board.getPositionByPiece(piece)
            }
        }
        throw RuntimeException("")
    }

    private fun createHistoryFromBoard(board: Board) : List<Board>{
        val historialBoards : MutableList<Board> = mutableListOf()
        historialBoards.add(board)
        return historialBoards
    }

    private fun chooseConfiguration() : Board {
        val continuar = true
        while (continuar) {
            println("Ingrese la configuración deseada: \n 1. Classic \n 2. Capablanca")
            val option: Int = sc.nextInt()
            return if (option == 1) {
                !continuar
                BoardFactory.createInitialClassicBoard()
            } else {
                !continuar
                BoardFactory.createInitialCapablancaBoard()
            }
        }
        return BoardFactory.createInitialClassicBoard()
    }

}