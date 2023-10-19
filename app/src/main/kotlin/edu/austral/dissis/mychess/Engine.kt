package edu.austral.dissis.mychess
import BoardFactory
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.piece.PieceColor
import edu.austral.dissis.mychess.moveResult.InvalidMovement
import edu.austral.dissis.mychess.moveResult.ValidMovement
import edu.austral.dissis.mychess.gameState.GameState
import edu.austral.dissis.mychess.moveResult.GameOver
import edu.austral.dissis.mychess.moveResult.MoveResult
import edu.austral.dissis.mychess.turnStrategy.RegularTurnStrategy
import edu.austral.dissis.mychess.turnStrategy.TurnStrategy
import edu.austral.dissis.mychess.validator.CheckMateValidator
import edu.austral.dissis.mychess.validator.KingInCheckValidator
import edu.austral.dissis.mychess.validator.Movement
import java.lang.RuntimeException
import java.util.*

class Engine {

    private val sc = Scanner(System.`in`)
    private val movementStrategy = MovementStrategy()
    private val adapter = Adapter()

    fun init() : GameState {
        //chooseConfiguration()
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
                gameState.getBoardsHistory().last()
            )
            if (newBoard == gameState.getBoardsHistory().last()){
                return InvalidMovement("Movimiento invalido para " +
                        pieceToMove.getId().takeWhile { it.isLetter() })
            }
            val kingColor = pieceToMove.getPieceColor()
            val kingPosition : Position = findKingPosition(newBoard, kingColor)

            // Verificar si el rey del jugador actual está en jaque en el nuevo tablero
            val kingInCheckValidator = KingInCheckValidator()
            val checkMateValidator = CheckMateValidator()
            if (kingInCheckValidator.isKingInCheck(newBoard, kingPosition, kingColor)){
                return InvalidMovement("Tu rey está en jaque")
            }

            val history : List<Board> = createHistoryFromBoard(newBoard)
            val advanceTurn = turnStrategy.advanceTurn(pieceToMove.getPieceColor())
            adapter.saveHistory(GameState(advanceTurn, history))

            // Verificar si el jugador actual está en jaque mate
            if (checkMateValidator.validateMovement(history.last(), kingPosition, kingColor, movement)){
                return GameOver("Jaque Mate!")
            }

            return ValidMovement
        }
    }

    private fun findKingPosition(board: Board, kingColor: PieceColor) : Position{
        for (piece in board.getPiecesPositions().values){
            if (piece::class.simpleName == "King" && piece.getPieceColor() == kingColor){
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