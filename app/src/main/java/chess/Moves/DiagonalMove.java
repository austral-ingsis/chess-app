package chess.Moves;

import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.Move;
import chess.Models.SideColor;

public class DiagonalMove implements Move {
    private int rowsIncremented;
    private int columnIncremented;
    private final boolean canJump;
    private boolean limitlessRow;
    private boolean limitlessColumn;
    public DiagonalMove(int rowsIncremented, int columnIncremented, boolean canJump) {
        if(rowsIncremented == 0 && columnIncremented == 0){
            limitlessRow = true;
            limitlessColumn = true;
        }
        else if(rowsIncremented == 0){
            limitlessRow = true;
        }
        else if(columnIncremented == 0){
            limitlessColumn = true;
        }
        this.rowsIncremented = rowsIncremented;
        this.columnIncremented = columnIncremented;
        this.canJump = canJump;
    }
    public DiagonalMove(boolean canJump){
        limitlessRow = true;
        limitlessColumn = true;
        this.canJump = canJump;
    }

    @Override
    public Boolean checkMove(Coordinate initialSquare, Coordinate finalSquare, Board board, SideColor color) {
        checkLimitless(board);
        if (Math.abs(finalSquare.column() - initialSquare.column()) != Math.abs(finalSquare.row() - initialSquare.row()))
            return false;
        checkForDirection(initialSquare, finalSquare);
        if (canJump) {
            return finalSquare.column() == initialSquare.column() + columnIncremented && finalSquare.row() == initialSquare.row() + rowsIncremented;
        } else {
            if(isDiagonalClear(board,initialSquare, finalSquare))
                return finalSquare.column() == initialSquare.column() + columnIncremented && finalSquare.row() == initialSquare.row() + rowsIncremented;
        }
        return false;
    }


    public boolean isDiagonalClear(Board board, Coordinate initialSquare, Coordinate finalSquare) {
        int rowIncrement = Integer.compare(finalSquare.row(), initialSquare.row());
        int columnIncrement = Integer.compare(finalSquare.column(), initialSquare.column());

        int rowCount = Math.abs(finalSquare.row() - initialSquare.row());
        int columnCount = Math.abs(finalSquare.column() - initialSquare.column());

        for (int i = 1; i < rowCount; i++) {
            int rowToCheck = initialSquare.row() + i * rowIncrement;
            int colToCheck = initialSquare.column() + i * columnIncrement;
            Coordinate coordinate = new Coordinate(rowToCheck, colToCheck);

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

    private void checkLimitless(Board board) {
        if (limitlessRow){
            rowsIncremented = board.getRows();
        }
        if (limitlessColumn){
            columnIncremented = board.getColumns();
        }
    }
}
