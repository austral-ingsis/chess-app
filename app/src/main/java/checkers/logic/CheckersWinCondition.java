package checkers.logic;

import common.models.Board;
import common.models.Coordinate;
import common.models.Piece;
import common.logic.WinCondition;
import common.results.MoveResults;

public class CheckersWinCondition implements WinCondition {
    private final CheckForWinByNoMorePieces CheckForWinByNoMorePieces = new CheckForWinByNoMorePieces();
    private final CheckForWinByNoMoreMoves CheckForWinByNoMoreMoves = new CheckForWinByNoMoreMoves();

    @Override
    public MoveResults<Board, Boolean> checkWin(Board board, Piece piece, MoveResults<Board, Boolean> move, Coordinate toSquare) {
        Board moveBoard = move.successfulResult();
        if (CheckForWinByNoMorePieces.check(moveBoard, piece.getColor())) {
            return new MoveResults<>(moveBoard, true, "CheckMate");
        }
        if (CheckForWinByNoMoreMoves.check(piece, moveBoard, board.getSquareOfPiece(piece).successfulResult().get())) {
            return new MoveResults<>(moveBoard, true, "CheckMate");
        } else
            return move;
    }
}
