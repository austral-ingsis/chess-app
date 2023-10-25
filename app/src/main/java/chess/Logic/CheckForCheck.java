package chess.Logic;

import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.Piece;
import chess.Models.SideColor;


public class CheckForCheck {
    public boolean check(Board board, SideColor color, Piece piece, Coordinate toSquare){
        if (!piece.isImportant()) {
            piece = board.findImportantPiece(color).successfulResult().get();
            toSquare = board.getSquareOfPiece(piece).successfulResult().get();
        }
        return forCheck(board, color, toSquare);
    }

    public Boolean forCheck(Board board, SideColor color, Coordinate toSquare){
        for (Piece p : board.getPieces()) {
            if (p.getColor() != color) {
                if (p.checkForCheck(toSquare, board))
                    return true;
            }
        }
        return false;
    }
}
