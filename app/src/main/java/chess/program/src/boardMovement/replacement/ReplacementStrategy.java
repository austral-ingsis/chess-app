package chess.program.src.boardMovement.replacement;

import chess.program.src.Board;
import chess.program.src.Position;

public interface ReplacementStrategy {

    public boolean replace(Board board, Position initial, Position finalPosition);

}
