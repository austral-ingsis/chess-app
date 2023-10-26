package chess.Logic.interfaces;

import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.Piece;
import chess.Results.MoveResults;

public interface WinCondition {
    MoveResults<Board,Boolean> checkWin(Board board, Piece piece, MoveResults<Board,Boolean> move, Coordinate toSquare);
}
