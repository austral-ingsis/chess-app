package chess.program.src.movement2;

import chess.program.src.Board;
import chess.program.src.Piece;
import chess.program.src.Position;

public class HorizontalAndVerticalMovement implements Movement2 {

    private int left;
    private int right;
    private int foward;
    private int backwards;

    public HorizontalAndVerticalMovement(int left1, int right1, int foward1, int backwards1) {
        this.left = left1;
        this.right = right1;
        this.foward = foward1;
        this.backwards = backwards1;
    }


    @Override
    public boolean move(Position inicial, Position finalPosition) {
        int x = finalPosition.getRow() - inicial.getRow();
        int y = finalPosition.getColumn() - inicial.getColumn();
        if (x == 0 || y == 0) {
            if (y == 0) {
                return (x >= (-1 * backwards) && x <= foward);
            } else {
                return (y >= -left && y <= right);
            }
        }
        return false;
    }

    @Override
    public boolean validate(Position initial, Position finalPosition, Board board) {
        int x = initial.getRow() - finalPosition.getRow();
        int y = initial.getColumn() - finalPosition.getColumn();
        if (Math.abs(x) != 0 && Math.abs(y) != 0) {
            return true;
        }


        if (Math.abs(x) == 0) {
            int minCol = Math.min(initial.getColumn(), finalPosition.getColumn());
            int maxCol = Math.max(initial.getColumn(), finalPosition.getColumn());
            int col = minCol + 1;
            while (col < maxCol) {
                Position position = new Position(initial.getRow(), col);
                Piece pieceInBetween = board.getPiece(position);
                if (pieceInBetween != null) {
                    return false;
                }
                col++;
            }
            return true;
        } else {
            int minRow = Math.min(initial.getRow(), finalPosition.getRow());
            int maxRow = Math.max(initial.getRow(), finalPosition.getRow());
            int row = minRow + 1;
            while (row < maxRow) {
                Position position = new Position(row, initial.getColumn());
                Piece pieceInBetween = board.getPiece(position);
                if (pieceInBetween != null) {
                    return false;
                }
                row++;
            }
            return true;
        }


    }

}
