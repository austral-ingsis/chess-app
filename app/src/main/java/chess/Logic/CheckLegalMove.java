package chess.Logic;

import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.Move;
import chess.Models.Piece;
import chess.Results.MoveResults;

import java.util.List;

public class CheckLegalMove{
    private final CheckForCheckMate checkForCheckMate = new CheckForCheckMate();
    private final CheckForCheck checkForCheck = new CheckForCheck();
    private final CheckMoveInList checkMoveInList = new CheckMoveInList();
    private final PossibleMovements possibleMovements = new PossibleMovements();

    public MoveResults<Board, Boolean> check(Piece piece, Coordinate toSquare, Board board, Coordinate initial, List<Move> movements){
        MoveResults<Board, Boolean> move = checkMoveInList.check(board, initial, toSquare, movements,piece);
        if (move.errorResult())
            return move;
        else {
            Board moveBoard = move.successfulResult();
            if (checkForCheckMate.check(moveBoard,piece.getColor(),moveBoard.getPieces(),possibleMovements,checkForCheck))
                return new MoveResults<>(moveBoard, true, "CheckMate");
            if (checkForCheck.check(moveBoard,piece.getColor(),piece, toSquare))
                return new MoveResults<>(board, true, "Check Rule unfollowed");
            else
                return move;
        }
    }
}
