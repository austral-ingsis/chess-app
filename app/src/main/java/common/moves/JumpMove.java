package common.moves;

import chess.models.Board;
import chess.models.Coordinate;
import chess.models.SideColor;

public class JumpMove implements Move {
    private final int rowsIncremented;
    private final int columnIncremented;

    public JumpMove(int rowsIncremented, int columnIncremented) {
        this.rowsIncremented = rowsIncremented;
        this.columnIncremented = columnIncremented;
    }
    @Override
    public Boolean checkMove(Coordinate initialSquare, Coordinate finalSquare, Board board, SideColor color) {
        return finalSquare.column() == initialSquare.column() + columnIncremented && finalSquare.row() == initialSquare.row() + rowsIncremented;
    }

}
