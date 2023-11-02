package common.moves;


import chess.models.Board;
import chess.models.Coordinate;
import chess.models.SideColor;

public interface Move {
    Boolean checkMove(Coordinate initialSquare, Coordinate finalSquare, Board board, SideColor color);

}
