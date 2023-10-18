
import board.Board
import edu.austral.dissis.mychess.Adapter
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

    fun getAdapter():Adapter{
        return adapter
    }

    fun init() : GameState {
        //chooseConfiguration()
        val board = BoardFactory.createInitialClassicBoard()
        val historicalBoards : List<Board> = createHistoryFromBoard(board)
        val turnStrategy : TurnStrategy = RegularTurnStrategy(PieceColor.WHITE)
        val gameState = GameState(turnStrategy, historicalBoards)
        adapter.saveHistory(gameState)
        return gameState
    }

    private fun createHistoryFromBoard(board: Board) : List<Board>{
        val historialBoards : MutableList<Board> = mutableListOf()
        historialBoards.add(board)
        return historialBoards
    }

    fun applyMove(movement: Movement, gameState: GameState) : MoveResult {
        val pieceToMove : Piece = movement.piece
        val turnStrategy : TurnStrategy = RegularTurnStrategy(pieceToMove.getPieceColor())
        val toPosition : Position = movement.finalPosition
        return if (pieceToMove.getPieceColor() != turnStrategy.getCurrentColor()){
            InvalidMovement("Es el turno del color " + turnStrategy.getCurrentColor())
            TODO("verificar que pasa cuando es movimiento invalido, ahora devuelve el mismo board pero cambia" +
                    " el turno igual")
        }else{
            val newBoard : Board = movementStrategy.moveTo(pieceToMove, toPosition,
                gameState.getBoardsHistory()[gameState.getBoardsHistory().size-1]
            )
            val historicalBoards : List<Board> = createHistoryFromBoard(newBoard)
            val advanceTurn = turnStrategy.advanceTurn(pieceToMove.getPieceColor())
            adapter.saveHistory(GameState(advanceTurn, historicalBoards))
            ValidMovement(newBoard.getPiecesPositions(), advanceTurn.getCurrentColor(), newBoard.getPositions())
        }

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