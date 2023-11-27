package edu.austral.dissis.common

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.commonValidators.Movement
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.PieceColor
import edu.austral.dissis.common.result.FailureResult
import edu.austral.dissis.common.result.FinishGameResult
import edu.austral.dissis.common.result.SuccessfulResult

class Adapter(private var game: Game) : GameEngine{

    override fun init(): InitialState {
        return InitialState(getBoardSize(), getPieces(), getCurrentPlayer())
    }

    override fun applyMove(move: Move): MoveResult {
        val from = adaptPositionToMyPosition(move.from)
        val to = adaptPositionToMyPosition(move.to)
        return when (val result = game.move(Movement(from, to))){
            is FailureResult -> InvalidMove(result.reason)
            is SuccessfulResult -> getNewGameState(result)
            is FinishGameResult -> GameOver(adaptPieceColorToPlayerColor(result.winner))
        }
    }

    private fun getCurrentPlayer(): PlayerColor {
        return if (game.getTurn() == PieceColor.WHITE){
            PlayerColor.WHITE
        }else{
            PlayerColor.BLACK
        }
    }

    private fun getPieces(): List<ChessPiece> {
        val board = game.getBoard()
        return adaptPiecesToChessPieces(board, board.getOccupiedPositions())
    }

    private fun adaptMyPositionToPosition(position: Position): edu.austral.dissis.chess.gui.Position {
        return edu.austral.dissis.chess.gui.Position(position.x, position.y)
    }

    private fun adaptPieceColorToPlayerColor(pieceColor: PieceColor): PlayerColor {
        return if (pieceColor == PieceColor.WHITE) PlayerColor.WHITE
        else PlayerColor.BLACK
    }

    private fun adaptPieceToChessPiece(board: Board, piece: Piece): ChessPiece {
        val position : edu.austral.dissis.chess.gui.Position = adaptMyPositionToPosition(board.getPositionByPiece(piece))
        val playerColor: PlayerColor = adaptPieceColorToPlayerColor(piece.color)
        val pieceNumber : String = piece.id.dropWhile { it.isLetter() }
        val pieceName : String = piece.pieceType.toString().lowercase()
        return ChessPiece(pieceNumber, playerColor, position, pieceName)
    }

    private fun adaptPiecesToChessPieces(board: Board, pieces: List<Piece>): List<ChessPiece> {
        val chessPieces: MutableList<ChessPiece> = mutableListOf()
        for (piece in pieces) {
            chessPieces.add(adaptPieceToChessPiece(board, piece))
        }
        return chessPieces.toList()
    }

    private fun getBoardSize(): BoardSize {
        val board = game.getBoard()
        return BoardSize(board.getSizeY(), board.getSizeX())
    }

    private fun adaptPositionToMyPosition(position: edu.austral.dissis.chess.gui.Position): Position{
        return Position(position.row, position.column)
    }

    private fun getNewGameState(result: SuccessfulResult): MoveResult {
        game = result.game
        return NewGameState(getPieces(), getCurrentPlayer())
    }
}
