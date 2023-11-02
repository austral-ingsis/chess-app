package chess.logic.classicGame;

import chess.Logic.interfaces.CheckLegalMove;
import chess.Logic.interfaces.WinCondition;
import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Moves.interfaces.Move;
import chess.Models.Piece;
import chess.Results.MoveResults;

import java.util.List;

public class CheckClassicLegalMove implements CheckLegalMove {
    private final CheckMoveInList checkMoveInList = new CheckMoveInList();

    @Override
    public MoveResults<Board, Boolean> check(Piece piece, Coordinate toSquare, Board board, Coordinate initial,List<Move> moves, WinCondition winCondition) {
        MoveResults<Board, Boolean> move = checkMoveInList.check(board, initial, toSquare, moves,piece);
        if (move.errorResult())
            return move;
        else {
            return winCondition.checkWin(board, piece, move, toSquare);
        }
    }
}
