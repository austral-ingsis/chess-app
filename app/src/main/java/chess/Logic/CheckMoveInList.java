package chess.Logic;

import chess.Models.*;
import chess.Results.MoveResults;

import java.util.ArrayList;
import java.util.List;

public class CheckMoveInList {
    public MoveResults<Board,Boolean> check(Board board, Coordinate initial, Coordinate toSquare, List<Move> movements, Piece piece){
        for (Move move : movements) {
            if (move.checkMove(initial, toSquare, board, piece.getColor())) {
                Board newBoard = board.positionPiece(piece, toSquare);
                Board newBoard2 = newBoard.positionPiece(null, initial);
                List<MovementHistory> newMovement = new ArrayList<>(board.getMovements());
                MovementHistory movement = new MovementHistory(initial, toSquare, piece);
                newMovement.add(movement);

                Board b = new Board(board.getRows(), board.getColumns(), newBoard2.getPieces(), newBoard2.getSquares(), newMovement);
                return new MoveResults<>(b, false, "Piece Moved");
            }
        }
        return new MoveResults<>(board, true, "Piece not moved");
    }
}
