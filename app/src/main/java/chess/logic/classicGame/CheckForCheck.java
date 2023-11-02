package chess.logic.classicGame;

import common.models.Board;
import common.models.Coordinate;
import common.models.Piece;
import common.models.SideColor;
import common.moves.Move;

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
            if (move.checkMove(board.getSquareOfPiece(piece).successfulResult().get(), toPosition, board, piece.getColor()).outputResult()) {
                return true;
            }
        }
        return false;
    }
}
