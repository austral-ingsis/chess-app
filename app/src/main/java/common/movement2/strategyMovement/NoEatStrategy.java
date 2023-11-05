package common.movement2.strategyMovement;

import common.Board;
import common.Position;
import common.movement2.Movement2;

public class NoEatStrategy implements Movement2 {
    @Override
    public boolean move( Board board, Position initial, Position finalPosition) {
        return board.getPiece(finalPosition) == null;
    }
}
