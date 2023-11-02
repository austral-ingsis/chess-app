package common.boardValidator;

import common.Board;
import common.Position;
import common.enums.Color;

public class OccupiedByTeamPiece implements Validator{
    @Override
    public boolean validate(Position initial, Position finalPosition, Board board) {
        Color color = board.getPiece(initial).getColor();
        if(board.getPiece(finalPosition) == null){return true;}
        return (board.getPiece(finalPosition).getColor() != color);
    }
}
