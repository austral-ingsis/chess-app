package chess.program.src.boardMovement;

import common.Board;
import common.BoardResult;
import common.Position;

public interface BoardMovement {

    BoardResult move(Board board, Position inicial, Position finalPosition);

}
