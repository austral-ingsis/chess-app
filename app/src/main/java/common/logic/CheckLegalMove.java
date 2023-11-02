package common.logic;

import chess.logic.classicGame.PieceMover;
import common.models.Board;
import common.models.Coordinate;
import common.moves.Move;
import common.models.Piece;
import common.results.MoveResults;

import java.util.List;

public class CheckLegalMove {
    private final PieceMover pieceMover = new PieceMover();

    public MoveResults<Board, Boolean> check(Piece piece, Coordinate toSquare, Board board, Coordinate initial,List<Move> moves, WinCondition winCondition) {
        MoveResults<Board, Boolean> move = pieceMover.check(board, initial, toSquare, moves,piece, board.getSquare(toSquare).getPiece());
        if (move.errorResult())
            return move;
        else {
            return winCondition.checkWin(board, piece, move, toSquare);
        }
    }
}