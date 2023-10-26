package common;


import common.enums.Color;
import common.enums.Type;
import common.movement2.Movement2;

import java.util.List;

public interface Piece {
   Boolean moveValidation(Position initial, Position finalPosition, Board board );
   Type getType();
   Color getColor();

   String getId();

   void setId(String id);

   List<Movement2> getMovements();

}
