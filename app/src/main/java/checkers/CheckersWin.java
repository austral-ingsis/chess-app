package checkers;

import common.Board;
import common.Piece;
import common.Position;
import common.enums.Color;
import common.winningCondition.WinCondition;

import java.util.ArrayList;
import java.util.List;

public class CheckersWin implements WinCondition {
    private Color color ;

    @Override
    public boolean winCondition(Board board, Position initial, Position finalPosition) {
        Piece piece = board.getPiece(finalPosition);
        if (piece.getColor() == Color.WHITE) {color = Color.BLACK;}
        else {color = Color.WHITE;}

        List<Position> positions = board.getAllPositions();
        List<Position> colorInCheckPositions = getPositionsByPieceColor(board, positions, color);

        return colorInCheckPositions.isEmpty();

    }


    private List<Position> getPositionsByPieceColor(Board board, List<Position> pos, Color color) {
        List<Position> positions = new ArrayList<>();
        for (Position position : pos) {
            Piece piece = board.getPiece(position);
            if (piece != null) {
                if (piece.getColor() == color) {
                    positions.add(position);
                }
            }
        }
        return positions;}


}
