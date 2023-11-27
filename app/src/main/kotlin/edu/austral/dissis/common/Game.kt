package edu.austral.dissis.common

import edu.austral.dissis.checkers.CheckersTurnStrategy
import edu.austral.dissis.checkers.CheckersWinCondition
import edu.austral.dissis.checkers.factory.CheckersPieceFactory
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.Result
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.turnStrategy.TurnStrategy
import edu.austral.dissis.common.turnStrategy.ClassicTurnStrategy
import edu.austral.dissis.mychess.ChessWinCondition
import edu.austral.dissis.mychess.factory.ChessPieceFactory

class Game(
    private val board: Board,
    private val currentTurn: PieceColor = PieceColor.WHITE,
    private val winCondition: WinCondition
) {
    fun move(movement: Movement): Result{
        val movementStrategy = MovementStrategy(getPieceFactory(board))
        val pieceToMove = board.getPiece(movement.from) ?: return FailureResult("That position is empty, try another one!")
        val newBoard = movementStrategy.moveTo(movement, board)
        val turnStrategy = getTurnStrategy(board, pieceToMove, newBoard, ClassicTurnStrategy(currentTurn))
        if (newBoard == board){
            return FailureResult("Invalid move for ${pieceToMove.pieceType}")
        }

        if (pieceToMove.color != turnStrategy.getCurrentColor()){
            return FailureResult("It's the ${turnStrategy.getCurrentColor().name} team's turn.")
        }

        val advancedTurn = turnStrategy.advanceTurn(pieceToMove.color)
        val winCondition = getWinCondition(board)
        //TODO(ver como manejar bien las condiciones de ganar)

        return SuccessfulResult(Game(newBoard, advancedTurn.getCurrentColor(), winCondition))

    }

    fun getBoard(): Board{
        return board
    }

    fun getTurn(): PieceColor{
        return currentTurn
    }

    private fun getTurnStrategy(currentBoard: Board, pieceToMove: Piece, newBoard: Board, currentTurn: TurnStrategy): TurnStrategy{
        val piecesNames = getAllNames(currentBoard)
        return if (piecesNames.contains("king")){
            ClassicTurnStrategy(currentTurn.getCurrentColor())
        }else{
            CheckersTurnStrategy(pieceToMove, currentBoard, currentTurn, newBoard)
        }
    }

    private fun getWinCondition(board: Board) : WinCondition{
        val piecesNames = getAllNames(board)
        return if (piecesNames.contains("king")){
            ChessWinCondition()
        }else{
            CheckersWinCondition()
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
}
