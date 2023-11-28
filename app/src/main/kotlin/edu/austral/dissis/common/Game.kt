package edu.austral.dissis.common

import edu.austral.dissis.checkers.CheckersTurnStrategy
import edu.austral.dissis.checkers.factory.CheckersPieceFactory
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.FinishGameResult
import edu.austral.dissis.common.result.Result
import edu.austral.dissis.common.result.SuccessfulResult
import edu.austral.dissis.common.turnStrategy.TurnStrategy
import edu.austral.dissis.common.turnStrategy.ClassicTurnStrategy
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
        if (isInvalidMove(newBoard)){
            return FailureResult("Invalid move for ${pieceToMove.pieceType}")
        }

        if (isOpponentsTurn(pieceToMove, turnStrategy)){
            return FailureResult("It's the ${turnStrategy.getCurrentColor().name} team's turn.")
        }

        val advancedTurn = turnStrategy.advanceTurn(pieceToMove.color)
        return when (val result = winCondition.validateWinCondition(board, movement)){
            is FailureResult -> FailureResult(result.reason)
            is FinishGameResult -> FinishGameResult(result.winner)
            is SuccessfulResult -> SuccessfulResult(Game(newBoard, advancedTurn.getCurrentColor(), winCondition))
        }
    }

    private fun isInvalidMove(newBoard: Board): Boolean{
        return newBoard == board
    }

    private fun isOpponentsTurn(pieceToMove: Piece, turnStrategy: TurnStrategy) : Boolean{
        return pieceToMove.color != turnStrategy.getCurrentColor()
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
