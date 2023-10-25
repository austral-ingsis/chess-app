package chess.Models;


public interface Move {
    Boolean checkMove(Coordinate initialSquare, Coordinate finalSquare, Board board, SideColor color);

}
