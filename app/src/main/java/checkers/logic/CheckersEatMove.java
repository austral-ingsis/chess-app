package checkers.logic;

import common.models.Board;
import common.models.Coordinate;
import common.models.SideColor;
import common.moves.Move;
import common.results.CheckResult;

public class CheckersEatMove implements Move {
    private final int rowsIncremented;
    private final int columnIncremented;

    CheckersEatMove(int rowsIncremented, int columnIncremented) {
        this.rowsIncremented = rowsIncremented;
        this.columnIncremented = columnIncremented;
    }

    @Override
    public CheckResult<Coordinate, Boolean> checkMove(Coordinate initialSquare, Coordinate finalSquare, Board board, SideColor color) {
        int resRow;
        if (checkSide(color)){
            resRow = finalSquare.row() + rowsIncremented;
        } else {
            resRow = finalSquare.row() - rowsIncremented;
        }
            if(checkColumn(initialSquare,finalSquare)) {
                Coordinate resSquare = new Coordinate(finalSquare.column() + columnIncremented, resRow);
                if (board.checkForPieceInSquare(resSquare) || !checkPiceInBoard(resSquare, board)) {
                    return new CheckResult<>(resSquare, false, "Jump Movement Failed");
                }
                return new CheckResult<>(resSquare, true, "Jump Movement Successful");
            }
        return new CheckResult<>(finalSquare, false, "Jump Movement Failed");
    }

    private boolean checkPiceInBoard(Coordinate resSquare, Board board) {
        int row = board.getRows();
        int column = board.getColumns();
        return resSquare.row() <= row && resSquare.row() > 0 && resSquare.column() <= column && resSquare.column() > 0;
    }

    private Boolean checkColumn(Coordinate initialSquare, Coordinate finalSquare){
        int direction = finalSquare.column() - initialSquare.column();
        int sign1 = Integer.signum(direction);
        int sign2 = Integer.signum(columnIncremented);
        return sign1 == sign2;
    }
    private Boolean checkSide(SideColor color) {
        return color == SideColor.White;
    }
    @Override
    public int getRowsIncremented() {
        return 0;
    }

    @Override
    public int getColumnIncremented() {
        return 0;
    }
}
