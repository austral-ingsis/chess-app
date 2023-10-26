package chess.Moves.interfaces;


import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.SideColor;

public interface Move {
    Boolean checkMove(Coordinate initialSquare, Coordinate finalSquare, Board board, SideColor color);

}
