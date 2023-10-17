package chess.program.src;

import chess.program.src.enums.Color;
import chess.program.src.enums.Type;
import chess.program.src.movement2.Movement2;

import java.util.ArrayList;
import java.util.List;

public class PieceImpl implements Piece{
    private Color color;
    private Type type;
    private List<Movement2> movements;
    private String id;


    public PieceImpl(Color color1, Type name1) {
        this.color = color1;
        this.type = name1;
        movements = new ArrayList<>();
        this.id = String.valueOf(hashCode());
    }
    public PieceImpl(Color color1, Type name1, List<Movement2> movements1) {
        this.color = color1;
        this.type = name1;
        this.movements = movements1;
        this.id = String.valueOf(hashCode());
    }

    public Boolean moveValidation(Position initial, Position finalPosition, Board board) {
        for( Movement2 movement : movements){
            if(movement.move(initial, finalPosition) && movement.validate(initial, finalPosition,board))
                return true;
        }
        return false;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public List<Movement2> getMovements() {
        return movements;
    }

    public String getId() {
        return id;
    }

}


