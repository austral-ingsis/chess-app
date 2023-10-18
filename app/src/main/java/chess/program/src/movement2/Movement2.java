package chess.program.src.movement2;

import chess.program.src.Board;
import chess.program.src.Position;

public interface Movement2 {

        boolean move(Position inicial, Position finalPosition);

        boolean validate(Position initial, Position finalPosition, Board board);
}
