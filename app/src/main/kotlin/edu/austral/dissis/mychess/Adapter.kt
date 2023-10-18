package edu.austral.dissis.mychess

import edu.austral.dissis.mychess.board.Board
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.mychess.moveResult.InvalidMovement
import edu.austral.dissis.mychess.moveResult.MoveResult
import edu.austral.dissis.mychess.moveResult.ValidMovement
import edu.austral.dissis.mychess.gameState.GameState
import edu.austral.dissis.mychess.piece.Piece
import edu.austral.dissis.mychess.piece.PieceColor
import edu.austral.dissis.mychess.validator.Movement

class Adapter {

    private fun adaptMyPositionToPosition(position: Position): edu.austral.dissis.chess.gui.Position {
        return edu.austral.dissis.chess.gui.Position(position.y, position.x)
    }

    private fun adaptPieceColorToPlayerColor(pieceColor: PieceColor): PlayerColor {
        return if (pieceColor == PieceColor.WHITE) PlayerColor.WHITE
        else PlayerColor.BLACK
    }

    private fun adaptPieceToChessPiece(board: Board, piece: Piece): ChessPiece {
        val position : edu.austral.dissis.chess.gui.Position = adaptMyPositionToPosition(board.getPositionByPiece(piece))
        val playerColor: PlayerColor = adaptPieceColorToPlayerColor(piece.getPieceColor())
        val pieceNumber : String = piece.getId().dropWhile { it.isLetter() }
        val pieceName : String = piece.getId().takeWhile { it.isLetter() }
        return ChessPiece(pieceNumber, playerColor, position, pieceName)
    }

    private fun adaptPiecesToChessPieces(board: Board, pieces: List<Piece>): List<ChessPiece> {
        val chessPieces: MutableList<ChessPiece> = mutableListOf()
        for (piece in pieces) {
            chessPieces.add(adaptPieceToChessPiece(board, piece))
        }
        return chessPieces.toList()
    }

    private fun adaptBoardSize(x: Int, y: Int) : BoardSize{
        return BoardSize(y, x)
    }

    private var states : MutableList<GameState> = mutableListOf()

    fun saveHistory(gameState: GameState){
        states.add(gameState)

    }

    fun getLastState() : GameState {
        return states[states.size-1]
    }

    fun adaptGameStateToInitialState(gameState: GameState) : InitialState{
        val board : Board = gameState.getBoardsHistory()[gameState.getBoardsHistory().size-1]
        val boardSize = adaptBoardSize(board.getSizeX(), board.getSizeY())
        val chessPieces = adaptPiecesToChessPieces(board, board.getPiecesPositions().values.toList())
        val playerColor = adaptPieceColorToPlayerColor(gameState.getTurnStrategy().getCurrentColor())
        return InitialState(boardSize, chessPieces, playerColor)
    }

    fun adaptMyMoveResultToMoveResult(moveResult: MoveResult) : edu.austral.dissis.chess.gui.MoveResult{
        val gameState : GameState = states[states.size-1]
        val board : Board = gameState.getBoardsHistory()[gameState.getBoardsHistory().size-1]
        val chessPieces = adaptPiecesToChessPieces(board, board.getPiecesPositions().values.toList())
        val playerColor = adaptPieceColorToPlayerColor(gameState.getTurnStrategy().getCurrentColor())
        return when(moveResult){
            is InvalidMovement -> InvalidMove(moveResult.reason)
            is ValidMovement -> NewGameState(chessPieces, playerColor)
        }
    }

    fun translateMoveToMovement(move: Move) : Movement {
        val gameState : GameState = states[states.size-1]
        val board : Board = gameState.getBoardsHistory()[gameState.getBoardsHistory().size-1]
        return Movement(board.getPieceByPosition(Position(move.from.column, move.from.row)), Position(move.to.column, move.to.row))
    }
}
