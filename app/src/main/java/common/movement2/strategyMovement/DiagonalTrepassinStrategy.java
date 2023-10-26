package common.movement2.strategyMovement;

import common.Board;
import common.Piece;
import common.Position;

public class DiagonalTrepassinStrategy implements ValidateMovement{
    @Override
    public boolean validate(Position initial, Position finalPosition, Board board ) {
        int x = finalPosition.getRow() - initial.getRow();
        int y = finalPosition.getColumn() - initial.getColumn();
        if (Math.abs(x) != Math.abs(y) ){return true;}

        int rowIncrement = x > 0 ? 1 : -1;
        int colIncrement = y > 0 ? 1 : -1;

        int row = initial.getRow() + rowIncrement;
        int col = initial.getColumn() + colIncrement;

        while (row != finalPosition.getRow() || col != finalPosition.getColumn()) {
            Position position = new Position(row, col);
            Piece pieceInBetween = board.getPiece(position);
            if (pieceInBetween != null) {
                return false; // Hay una pieza en el camino diagonal, retorna false
            }
            row += rowIncrement;
            col += colIncrement;
        }

        return true; // No hay piezas en el camino diagonal, retorna true
    }
}
