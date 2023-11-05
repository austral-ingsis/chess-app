package common.movement2;

import common.Board;
import common.Position;

import java.util.List;

public class ComposeMovement implements Movement2{
    List<Movement2> movements;

    public ComposeMovement(List<Movement2> movements){
        this.movements = movements;
    }

    @Override
    public boolean move(Board board, Position inicial, Position finalPosition) {
        for (Movement2 movement : movements) {
            if(!movement.move(board, inicial, finalPosition)){
                return false;
            }
        }
        return true;
    }
}
