package edu.austral.dissis.mychess
import BoardFactory
import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.piece.PieceColor
import edu.austral.dissis.mychess.moveResult.InvalidMovement
import edu.austral.dissis.mychess.moveResult.ValidMovement
import edu.austral.dissis.mychess.gameState.GameState
import edu.austral.dissis.mychess.moveResult.MoveResult
import edu.austral.dissis.mychess.turnStrategy.ClassicTurnStrategy
import edu.austral.dissis.mychess.turnStrategy.TurnStrategy
import edu.austral.dissis.mychess.validator.specificValidators.CheckMateValidator
import edu.austral.dissis.mychess.validator.specificValidators.KingInCheckValidator
import edu.austral.dissis.mychess.validator.Movement
import java.lang.RuntimeException
import java.util.*

class Game {

    private val sc = Scanner(System.`in`)
    private val movementStrategy = MovementStrategy()
    private val adapter = Adapter()

    fun init() : GameState {
        val board = chooseConfiguration()
        val historicalBoards : List<Board> = createHistoryFromBoard(board)
        val turnStrategy : TurnStrategy = ClassicTurnStrategy(PieceColor.WHITE)
        val gameState = GameState(turnStrategy, historicalBoards)
        adapter.saveHistory(gameState)
        return gameState
    }

    fun applyMove(movement: Movement, gameState: GameState) : MoveResult {
        val pieceToMove : Piece = movement.piece
        val turnStrategy : TurnStrategy = gameState.getTurnStrategy()
        val toPosition : Position = movement.finalPosition
        if (pieceToMove.color != turnStrategy.getCurrentColor()){
            return InvalidMovement("Es el turno del color " + turnStrategy.getCurrentColor())
        }else{
            val newBoard : Board = movementStrategy.moveTo(pieceToMove, toPosition,
                gameState.getBoardsHistory().last()
            )
            if (newBoard == gameState.getBoardsHistory().last()){
                return InvalidMovement("Movimiento invalido para " +
                        pieceToMove.id.takeWhile { it.isLetter() })
            }
            val kingColor = pieceToMove.color
            val kingPosition : Position = findKingPosition(newBoard, kingColor)

            // Verificar si el rey del jugador actual está en jaque en el nuevo tablero
            val kingInCheckValidator = KingInCheckValidator()
            val checkMateValidator = CheckMateValidator()
            if (kingInCheckValidator.isKingInCheck(newBoard, kingPosition, kingColor)){
                return InvalidMovement("Tu rey está en jaque")
            }

            val history : List<Board> = createHistoryFromBoard(newBoard)
            val advanceTurn = turnStrategy.advanceTurn(pieceToMove.color)
            adapter.saveHistory(GameState(advanceTurn, history))

            // Verificar si el jugador actual está en jaque mate
//            if (checkMateValidator.validateMovement(history.last(), kingPosition, kingColor, movement)){
//                return GameOver("Jaque Mate!")
//            }

            return ValidMovement
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

    fun getAdapter():Adapter{
        return adapter
    }

    private fun chooseConfiguration() : Board {
        val continuar = true
        while (continuar) {
            println("Ingrese la configuración deseada: \n 1. Classic \n 2. Capablanca") //2. Capablanca
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