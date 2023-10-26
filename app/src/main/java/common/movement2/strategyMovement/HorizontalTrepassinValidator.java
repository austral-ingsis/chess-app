package common.movement2.strategyMovement;

import common.Board;
import common.Piece;
import common.Position;

public class HorizontalTrepassinValidator implements ValidateMovement{
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
