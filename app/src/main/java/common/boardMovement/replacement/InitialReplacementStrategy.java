package common.boardMovement.replacement;

import common.Board;
import common.Position;

public class InitialReplacementStrategy implements ReplacementStrategy{
    @Override
    public boolean replace(Board board, Position initial, Position finalPosition) {
        return true;
    }
}
