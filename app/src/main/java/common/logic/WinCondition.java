package common.logic;

import chess.models.Board;
import chess.models.Coordinate;
import chess.models.Piece;
import common.results.MoveResults;

public interface WinCondition {
    MoveResults<Board,Boolean> checkWin(Board board, Piece piece, MoveResults<Board,Boolean> move, Coordinate toSquare);
}
