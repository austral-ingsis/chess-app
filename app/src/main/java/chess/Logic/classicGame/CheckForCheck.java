package chess.Logic.classicGame;

import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.Piece;
import chess.Models.SideColor;
import chess.Moves.interfaces.Move;

public class CheckForCheck {
    public boolean check(Board board, SideColor color, Piece piece, Coordinate toSquare){
        if (!piece.isImportant()) {
            piece = board.findImportantPiece(color).successfulResult().get();
            toSquare = board.getSquareOfPiece(piece).successfulResult().get();
        }
        return forCheck(board, color, toSquare);
    }

    public Boolean forCheck(Board board, SideColor color, Coordinate toSquare){
        for (Piece piece : board.getCurrentPieces()) {
            if (piece.getColor() != color) {
                if (checkForEach(piece,toSquare, board))
                    return true;
            }
        }
        return false;
    }
    public Boolean checkForEach(Piece piece,Coordinate toPosition, Board board) {
        if (board.getSquareOfPiece(piece).successfulResult().isEmpty())
            return false;
        for (Move move : piece.getEatMovements()) {
            if (move.checkMove(board.getSquareOfPiece(piece).successfulResult().get(), toPosition, board, piece.getColor())) {
                return true;
            }
        }
        return false;
    }
}
