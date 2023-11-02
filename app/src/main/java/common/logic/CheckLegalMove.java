package common.logic;

import chess.models.Board;
import chess.models.Coordinate;
import chess.models.Piece;
import common.results.MoveResults;
import common.moves.Move;
import java.util.List;

public interface CheckLegalMove {
    MoveResults<Board, Boolean> check(Piece piece, Coordinate toSquare, Board board, Coordinate initial, List<Move> moves, WinCondition winCondition);
}
