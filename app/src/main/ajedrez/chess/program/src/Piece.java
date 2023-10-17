package chess.program.src;


import chess.program.src.enums.Color;
import chess.program.src.enums.Type;
import chess.program.src.movement2.Movement2;

import java.util.List;

public interface Piece {
   Boolean moveValidation(Position initial, Position finalPosition, Board board );
   Type getType();
   Color getColor();

   String getId();

}
