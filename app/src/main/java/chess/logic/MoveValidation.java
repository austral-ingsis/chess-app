package chess.logic;

import common.models.Board;
import common.models.Coordinate;
import common.moves.Move;
import common.models.Piece;

public class MoveValidation {
    public Boolean validateMove(Piece piece, Board board, Coordinate possibleMove, Coordinate initialSquare){
        for (Move move : piece.getMovements()) {
            if (move.checkMove(initialSquare, possibleMove, board, piece.getColor()).outputResult() && CommonRule.checkRule(board, piece, possibleMove)){
                return true;
            }
        }
        return false;
    }
}
