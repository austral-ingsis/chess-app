package chess.Moves;
import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.SideColor;
import chess.Moves.interfaces.Move;

import java.util.Objects;

public class VerticalMove implements Move {
    int rowsIncremented;
    private final boolean backwardMove;
    private final boolean limitless;

    public VerticalMove(int rowsIncremented, boolean backwardMove) {
        limitless = false;
        this.rowsIncremented = rowsIncremented;
        this.backwardMove = backwardMove;
    }

    public VerticalMove( boolean backwardMove){
        limitless = true;
        this.backwardMove = backwardMove;
    }

    @Override
    public Boolean checkMove(Coordinate initialSquare, Coordinate finalSquare, Board board, SideColor side) {
        checkLimitless(board);
        if (backwardMove) {
            return checkBackwardMove(initialSquare, finalSquare, board);
        }
        if (Objects.equals(side, SideColor.White)) {
            return isPathBlockedForward(initialSquare, finalSquare, board);
        } else {
                return isPathBlockedBackward(initialSquare, finalSquare, board);
        }
    }


    private Boolean checkBackwardMove(Coordinate initialSquare, Coordinate finalSquare, Board board) {
        if (finalSquare.row() > initialSquare.row()) {
            return isPathBlockedForward(initialSquare, finalSquare, board);
        } else
            return isPathBlockedBackward(initialSquare, finalSquare, board);
    }

    public boolean isPathBlockedForward(Coordinate initialSquare, Coordinate finalSquare, Board board){
        for (int i = 1; i < finalSquare.row() - initialSquare.row(); i++) {
            Coordinate coordinate = new Coordinate(initialSquare.column(), initialSquare.row() +i);
            if (board.checkForPieceInSquare(coordinate)){
                return false;
            }
        }
        if (!limitless)
            return finalSquare.column() == initialSquare.column() && finalSquare.row() == initialSquare.row() + rowsIncremented;
        else
            return finalSquare.column() == initialSquare.column();

    }
    public boolean isPathBlockedBackward(Coordinate initialSquare, Coordinate finalSquare, Board board){
        for (int i = 1; i < initialSquare.row() - finalSquare.row(); i++) {
            Coordinate coordinate = new Coordinate(initialSquare.column(), initialSquare.row() -i);
            if (board.checkForPieceInSquare(coordinate)){
                return false;
            }
        }
        if (!limitless)
            return finalSquare.column() == initialSquare.column() && finalSquare.row() == initialSquare.row() - rowsIncremented;
        else
            return finalSquare.column() == initialSquare.column();
    }

    private void checkLimitless(Board board) {
        if (limitless){
            rowsIncremented = board.getRows();
        }
    }

}