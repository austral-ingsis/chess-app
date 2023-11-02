package common.moves;

import common.models.Board;
import common.models.Coordinate;
import common.models.SideColor;
import common.results.CheckResult;


public class DiagonalMove implements Move {
    private int rowsIncremented;
    private int columnIncremented;
    private int rowsCount;
    private int columnCount;
    public DiagonalMove(int rowsIncremented, int columnIncremented) {
        this.rowsIncremented = rowsIncremented;
        this.columnIncremented = columnIncremented;
    }
    public DiagonalMove() {
    }

    @Override
    public CheckResult<Coordinate,Boolean> checkMove(Coordinate initialSquare, Coordinate finalSquare, Board board, SideColor color) {
        if (Math.abs(finalSquare.column() - initialSquare.column()) != Math.abs(finalSquare.row() - initialSquare.row()))
            return new CheckResult<>(finalSquare, false,"Diagonal Movement Failed");
        checkForDirection(initialSquare, finalSquare);
        if(isDiagonalClear(board,initialSquare, finalSquare))
            if (finalSquare.column() == initialSquare.column() + columnIncremented * columnCount && finalSquare.row() == initialSquare.row() + rowsIncremented * rowsCount){
                return new CheckResult<>(finalSquare, true,"Diagonal Movement Successful");
            } else {
                return new CheckResult<>(finalSquare, false,"Diagonal Movement Failed");
            }
        return new CheckResult<>(finalSquare, false,"Diagonal Movement Failed");
    }


    public boolean isDiagonalClear(Board board, Coordinate initialSquare, Coordinate finalSquare) {
        rowsCount = Integer.compare(finalSquare.row(), initialSquare.row());
        columnCount = Integer.compare(finalSquare.column(), initialSquare.column());

        rowsIncremented = Math.abs(finalSquare.row() - initialSquare.row());
        columnIncremented = Math.abs(finalSquare.column() - initialSquare.column());

        for (int i = 1; i < rowsIncremented; i++) {
            int rowToCheck = initialSquare.row() + i * rowsCount;
            int colToCheck = initialSquare.column() + i * columnCount;
            Coordinate coordinate = new Coordinate(colToCheck, rowToCheck);

            if (board.checkForPieceInSquare(coordinate)) {
                return false;
            }
        }
        return true;
    }


    private void checkForDirection(Coordinate initialSquare, Coordinate finalSquare) {
        if(finalSquare.column() < initialSquare.column()){
            columnIncremented *= -1;
        }
        if(finalSquare.row() < initialSquare.row()){
            rowsIncremented *= -1;
        }
    }

}
