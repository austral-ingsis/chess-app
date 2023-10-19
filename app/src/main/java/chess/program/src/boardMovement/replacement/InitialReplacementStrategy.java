package chess.program.src.boardMovement.replacement;

import chess.program.src.Board;
import chess.program.src.Position;

public class InitialReplacementStrategy implements ReplacementStrategy{
    @Override
    public boolean replace(Board board, Position initial, Position finalPosition) {
        return true;
    }
}
