package checkers;

import common.models.Board;
import common.models.Coordinate;
import common.models.SideColor;
import common.moves.Move;
import common.results.CheckResult;

public class ConsecuentMove implements Move {
    Move initialMove;
    Move finalMove;
    Coordinate newCord;
    public ConsecuentMove(Move initial, Move finalMove) {
        this.initialMove = initial;
        this.finalMove = finalMove;
    }

    @Override
    public CheckResult<Coordinate, Boolean> checkMove(Coordinate initialSquare, Coordinate finalSquare, Board board, SideColor color) {
       if(initialMove.checkMove(initialSquare, finalSquare, board, color).outputResult()) {
           newCord = new Coordinate(finalSquare.column() + finalMove.getColumnIncremented(), finalSquare.row() + finalMove.getRowsIncremented());
           if (finalMove.checkMove(finalSquare, newCord, board, color).outputResult()) {
               return new CheckResult<>(newCord, true, "Consequent Movement Successful");
           } else {
               return new CheckResult<>(finalSquare, false, "Consequent Movement Failed");
           }
       } else {
           return new CheckResult<>(finalSquare, false, "Consequent Movement Failed");
       }
    }

    @Override
    public int getRowsIncremented() {
        return finalMove.getRowsIncremented() - initialMove.getRowsIncremented();
    }

    @Override
    public int getColumnIncremented() {
        return finalMove.getColumnIncremented() - initialMove.getColumnIncremented();
    }

}
