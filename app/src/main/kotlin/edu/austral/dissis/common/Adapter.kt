package edu.austral.dissis.common

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.gameState.GameState
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceColor

class Adapter {

    private var states : MutableList<GameState> = mutableListOf()

    private fun adaptMyPositionToPosition(position: Position): edu.austral.dissis.chess.gui.Position {
        return edu.austral.dissis.chess.gui.Position(position.x, position.y)
    }

    fun adaptPieceColorToPlayerColor(pieceColor: PieceColor): PlayerColor {
        return if (pieceColor == PieceColor.WHITE) PlayerColor.WHITE
        else PlayerColor.BLACK
    }

    private fun adaptPieceToChessPiece(board: Board, piece: Piece): ChessPiece {
        val position : edu.austral.dissis.chess.gui.Position = adaptMyPositionToPosition(board.getPositionByPiece(piece))
        val playerColor: PlayerColor = adaptPieceColorToPlayerColor(piece.color)
        val pieceNumber : String = piece.id.dropWhile { it.isLetter() }
        val pieceName : String = piece.id.takeWhile { it.isLetter() }
        return ChessPiece(pieceNumber, playerColor, position, pieceName)
    }

    fun adaptPiecesToChessPieces(board: Board, pieces: List<Piece>): List<ChessPiece> {
        val chessPieces: MutableList<ChessPiece> = mutableListOf()
        for (piece in pieces) {
            chessPieces.add(adaptPieceToChessPiece(board, piece))
        }
        return chessPieces.toList()
    }

    private fun adaptBoardSize(x: Int, y: Int) : BoardSize{
        return BoardSize(y, x)
    }

    fun saveHistory(gameState: GameState){
        states.add(gameState)

    }

    fun getLastState() : GameState {
        return states.last()
    }

    fun adaptGameStateToInitialState(gameState: GameState) : InitialState{
        val board : Board = gameState.getBoardsHistory().last()
        val boardSize = adaptBoardSize(board.getSizeX(), board.getSizeY())
        val chessPieces = adaptPiecesToChessPieces(board, board.getPiecesPositions().values.toList())
        val playerColor = adaptPieceColorToPlayerColor(gameState.getTurnStrategy().getCurrentColor())
        return InitialState(boardSize, chessPieces, playerColor)
    }
}
