package chess.logic.classicGame;

import common.logic.WinCondition;
import chess.models.*;
import common.results.MoveResults;


public class ClassicWinCondition implements WinCondition {
    private final CheckForCheck checkForCheck = new CheckForCheck();
    private final CheckForCheckMate checkForCheckMate  = new CheckForCheckMate();
    @Override
    public MoveResults<Board,Boolean> checkWin(Board board, Piece piece, MoveResults<Board,Boolean> move, Coordinate toSquare) {
        Board moveBoard = move.successfulResult();
        if (checkForCheckMate.check(moveBoard,piece.getColor(),moveBoard.getPieces(),checkForCheck))
            return new MoveResults<>(moveBoard, true, "CheckMate");
        if (checkForCheck.check(moveBoard,piece.getColor(),piece, toSquare))
            return new MoveResults<>(board, true, "Check Rule unfollowed");
        else
            return move;
    }
}
