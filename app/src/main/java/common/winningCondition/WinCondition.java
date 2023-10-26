package common.winningCondition;

import common.Board;
import common.Position;

public interface WinCondition {

    public boolean winCondition(Board board, Position initial, Position finalPosition);

}
