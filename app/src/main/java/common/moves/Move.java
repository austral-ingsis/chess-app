package common.moves;


import common.models.Board;
import common.models.Coordinate;
import common.models.SideColor;
import common.results.CheckResult;

public interface Move {
    CheckResult<Coordinate,Boolean> checkMove(Coordinate initialSquare, Coordinate finalSquare, Board board, SideColor color);
}
