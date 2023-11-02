package common.boardValidator;

import common.Board;
import common.Position;

public interface Validator {

    boolean validate(Position initial, Position finalPosition, Board board);

}
