package chess.program.src.boardValidator;

import chess.program.src.Board;
import chess.program.src.Position;

public interface Validator {

    boolean validate(Position initial, Position finalPosition, Board board);

}
