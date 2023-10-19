package chess.program.src.movement2;

import chess.program.src.Board;
import chess.program.src.Piece;
import chess.program.src.Position;

public class DiagonalMovement implements Movement2{
    private int left;
    private int right;
    private int foward;
    private int backwards;


    public DiagonalMovement(int left1, int right1, int foward1, int backwards1){
        this.left = left1;
        this.right = right1;
        this.foward = foward1;
        this.backwards = backwards1;
    }

    @Override
    public boolean move(Position inicial, Position finalPosition) {
        int x = finalPosition.getRow() - inicial.getRow();
        int y = finalPosition.getColumn() - inicial.getColumn();
        if (Math.abs(x) == Math.abs(y)){
            if (y >= -left && y <= right){
                if (x <= foward && x >= -backwards){
                    return true;
                }
            }
        }
        return false;
    }

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
