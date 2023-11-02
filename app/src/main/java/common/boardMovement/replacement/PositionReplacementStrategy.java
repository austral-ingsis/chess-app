package common.boardMovement.replacement;

import common.Board;
import common.Position;

import java.util.List;

public class PositionReplacementStrategy implements ReplacementStrategy{

    private List<Position> positions;

    public PositionReplacementStrategy(List<Position> positions) {
        this.positions = positions;
    }
    @Override
    public boolean replace(Board board, Position initial, Position finalPosition) {
        for(Position position: positions){
            if(position.equals(finalPosition)){
                return true;
            }
        }
        return false;
    }
}
