package chess.program.src.boardMovement.replacement;

import common.Board;
import common.Position;

public interface ReplacementStrategy {

    public boolean replace(Board board, Position initial, Position finalPosition);

}
