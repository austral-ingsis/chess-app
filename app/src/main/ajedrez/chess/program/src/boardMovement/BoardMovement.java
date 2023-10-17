package chess.program.src.boardMovement;

import chess.program.src.Board;
import chess.program.src.BoardResult;
import chess.program.src.Position;

public interface BoardMovement {

    BoardResult move(Board board, Position inicial, Position finalPosition);

}
