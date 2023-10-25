package chess.Logic;

import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.Move;
import chess.Models.Piece;

import java.util.ArrayList;
import java.util.List;

public class PossibleMovements {
    public List<Coordinate> getPossibleMovements(Board board,Piece piece, Coordinate initialSquare) {
        List<Coordinate> possibleMoves = new ArrayList<>();
        for (Move move : piece.getEatMovements()) {
            calculatePossibleMoves(board, piece, initialSquare, possibleMoves, move);
        }
        for (Move move : piece.getMovements()) {
            calculatePossibleMoves(board, piece, initialSquare, possibleMoves, move);
        }
        return possibleMoves;
    }

    private void calculatePossibleMoves(Board board, Piece piece, Coordinate initialSquare, List<Coordinate> possibleMoves, Move move) {
        for (int i = 0; i < board.getColumns(); i++) {
            for (int j = 0; j < board.getRows(); j++) {
                Coordinate finalSquare = new Coordinate(i, j);
                if (move.checkMove(initialSquare, finalSquare, board, piece.getColor())) {
                    possibleMoves.add(finalSquare);
                }
            }
        }
    }
}
