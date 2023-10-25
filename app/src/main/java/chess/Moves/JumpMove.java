package chess.Moves;

import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.Move;
import chess.Models.SideColor;

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
