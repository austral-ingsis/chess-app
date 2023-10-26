package chess.Logic.interfaces;

import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.Piece;
import chess.Moves.interfaces.Move;
import chess.Results.MoveResults;

import java.util.List;

public interface CheckLegalMove {
    MoveResults<Board, Boolean> check(Piece piece, Coordinate toSquare, Board board, Coordinate initial, List<Move> moves, WinCondition winCondition);
}
