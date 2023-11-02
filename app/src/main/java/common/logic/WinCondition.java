package common.logic;

import common.models.Board;
import common.models.Coordinate;
import common.models.Piece;
import common.results.MoveResults;

public interface WinCondition {
    MoveResults<Board,Boolean> checkWin(Board board, Piece piece, MoveResults<Board,Boolean> move, Coordinate toSquare);
}
