package chess.logic;

import common.models.Board;
import common.models.Coordinate;
import common.moves.Move;
import common.models.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PossibleMovements {
    public List<Coordinate> getPossibleMovements(Board board,Piece piece, Coordinate initialSquare) {
        List<Coordinate> possibleMoves = new ArrayList<>();
        for (Move move : piece.getEatMovements()) {
            calculatePossibleMoves(board, piece, initialSquare, possibleMoves, move,true);
        }
        for (Move move : piece.getMovements()) {
            calculatePossibleMoves(board, piece, initialSquare, possibleMoves, move,false);
        }
        return possibleMoves;
    }


    private void calculatePossibleMoves(Board board, Piece piece, Coordinate initialSquare, List<Coordinate> possibleMoves, Move move,Boolean eat) {
        for (int i = 1; i <= board.getColumns(); i++) {
            for (int j = 1; j <= board.getRows(); j++) {
                Coordinate finalSquare = new Coordinate(i, j);
                if(eat) {
                    if (!Objects.equals(board.getSquare(finalSquare).getPiece().getName(), "null"))
                        if (CommonRule.checkRule(board,piece,finalSquare) && move.checkMove(initialSquare, finalSquare, board, piece.getColor()).outputResult())
                            if(!checkDuplicated(possibleMoves, finalSquare))
                                possibleMoves.add(finalSquare);
                } else if (move.checkMove(initialSquare, finalSquare, board, piece.getColor()).outputResult()&& CommonRule.checkRule(board,piece,finalSquare)) {
                    if(!checkDuplicated(possibleMoves, finalSquare))
                        possibleMoves.add(finalSquare);
                }
            }
        }
    }

    private Boolean checkDuplicated(List<Coordinate> possibleMoves, Coordinate finalSquare) {
        for (Coordinate possibleMove : possibleMoves) {
            if (possibleMove.equals(finalSquare)) {
                return true;
            }
        }
        return false;
    }
}
