package chess.Logic;

import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.Move;
import chess.Models.Piece;

public class MoveValidation {
    public Boolean validateMove(Piece piece, Board board, Coordinate possibleMove, Coordinate initialSquare){
        for (Move move : piece.getMovements()) {
            if (move.checkMove(initialSquare, possibleMove, board, piece.getColor()) && CommonRule.checkRule(board, piece, possibleMove)){
                return true;
            }
        }
        return false;
    }
}
