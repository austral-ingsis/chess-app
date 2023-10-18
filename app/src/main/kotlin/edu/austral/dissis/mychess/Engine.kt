
import board.Board
import edu.austral.dissis.mychess.Adapter
import edu.austral.dissis.mychess.MovementStrategy
import piece.Piece
import piece.PieceColor
import edu.austral.dissis.mychess.moveResult.InvalidMovement
import edu.austral.dissis.mychess.moveResult.MoveResult
import edu.austral.dissis.mychess.moveResult.ValidMovement
import gameState.GameState
import edu.austral.dissis.mychess.turnStrategy.RegularTurnStrategy
import turnStrategy.TurnStrategy
import validator.Movement
import java.util.*

class Engine {

    private val sc = Scanner(System.`in`)
    private val movementStrategy = MovementStrategy()
    private val adapter = Adapter()

    fun init() : GameState {
        chooseConfiguration()
        val board = BoardFactory.createInitialClassicBoard()
        val historicalBoards : List<Board> = createHistoryFromBoard(board)
        val turnStrategy : TurnStrategy = RegularTurnStrategy(PieceColor.WHITE)
        val gameState = GameState(turnStrategy, historicalBoards)
        adapter.saveHistory(gameState)
        return gameState
    }

    fun applyMove(movement: Movement, gameState: GameState) : MoveResult {
        val pieceToMove : Piece = movement.piece
        val turnStrategy : TurnStrategy = gameState.getTurnStrategy()
        val toPosition : Position = movement.finalPosition
        if (pieceToMove.getPieceColor() != turnStrategy.getCurrentColor()){
            return InvalidMovement("Es el turno del color " + turnStrategy.getCurrentColor())
        }else{
            val newBoard : Board = movementStrategy.moveTo(pieceToMove, toPosition,
                gameState.getBoardsHistory()[gameState.getBoardsHistory().size-1]
            )
            if (newBoard == gameState.getBoardsHistory()[gameState.getBoardsHistory().size-1]){
                return InvalidMovement("Movimiento invalido para " +
                        pieceToMove.getId().takeWhile { it.isLetter() })
            }
            val history : List<Board> = createHistoryFromBoard(newBoard)
            val advanceTurn = turnStrategy.advanceTurn(pieceToMove.getPieceColor())
            adapter.saveHistory(GameState(advanceTurn, history))
            return ValidMovement(newBoard.getPiecesPositions(), advanceTurn.getCurrentColor(),
                newBoard.getPositions())
        }
    }

    private fun createHistoryFromBoard(board: Board) : List<Board>{
        val historialBoards : MutableList<Board> = mutableListOf()
        historialBoards.add(board)
        return historialBoards
    }

    fun getAdapter():Adapter{
        return adapter
    }

    private fun chooseConfiguration() {
        var continuar = true
        while (continuar) {
            println("Ingrese la configuración deseada: \n 1. Classic ") //2. Capablanca
            val option: Int = sc.nextInt()
            continuar = if (option == 1) {
                //turnStrategy = RegularTurnStrategy(PieceColor.WHITE)
                false
            } else {
                //boardFactory.createOtherBoard()
                false
            }
        }
    }
}