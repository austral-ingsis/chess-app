package common.movement2.strategyMovement;

import common.Board;
import common.Position;

public class NoEatStrategy implements ValidateMovement{
    @Override
    public boolean validate(Position initial, Position finalPosition, Board board) {
        return board.getPiece(finalPosition) == null;
    }
}
