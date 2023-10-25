package chess.Logic;


import chess.Models.Coordinate;
import chess.Models.Board;
import chess.Models.Piece;

import java.util.Objects;

public class CommonRule {
    public static Boolean checkRule(Board board, Piece piece, Coordinate toSquare) {
        if (board.getSquareOfPiece(piece) == null) {
            return false;
        }
        if (toSquare.column() > board.getColumns() || toSquare.row() > board.getRows() || toSquare.column() <= 0 || toSquare.row() <= 0) {
            return false;
        }
        if (toSquare.column() == board.getSquareOfPiece(piece).successfulResult().get().column() && toSquare.row() == board.getSquareOfPiece(piece).successfulResult().get().row()){
            return false;
        }
        return board.getSquare(toSquare).getPiece() == null || !Objects.equals(board.getSquare(toSquare).getPiece().getColor(), piece.getColor());
    }
}
