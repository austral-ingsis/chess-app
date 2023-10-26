package common.movement2.strategyMovement;

import common.Board;
import common.Position;

public interface ValidateMovement {

    boolean validate(Position initial, Position finalPosition, Board board);

}
