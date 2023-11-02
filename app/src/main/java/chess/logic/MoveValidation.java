package chess.logic;

import chess.models.Board;
import chess.models.Coordinate;
import common.moves.Move;
import chess.models.Piece;

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
