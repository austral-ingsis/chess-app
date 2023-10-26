package common.movement2;

import common.Board;
import common.Position;

public interface Movement2 {

        boolean move( Position inicial, Position finalPosition);

        boolean checkMoveStrategies(Board board, Position inicial, Position finalPosition);

}
