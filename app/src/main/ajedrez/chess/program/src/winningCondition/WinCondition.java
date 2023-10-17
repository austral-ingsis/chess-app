package chess.program.src.winningCondition;

import chess.program.src.Board;
import chess.program.src.Position;

public interface WinCondition {

    public boolean winCondition(Board board, Position initial, Position finalPosition);

}
