package common;

import common.enums.Color;
import common.enums.Type;
import common.movement2.ComposeMovement;
import common.movement2.Movement2;

import java.util.List;

public class PieceImpl implements Piece{
    private Color color;
    private Type type;
    private List<ComposeMovement> movements;
    private String id;


    public PieceImpl(Piece piece) {
        this.color = piece.getColor();
        this.type = piece.getType();
        movements = piece.getMovements();
        this.id = String.valueOf(hashCode());
    }
    public PieceImpl(Color color1, Type name1, List<ComposeMovement> movements1) {
        this.color = color1;
        this.type = name1;
        this.movements = movements1;
        this.id = String.valueOf(hashCode());
    }

    public Boolean moveValidation(Position initial, Position finalPosition, Board board) {
        for( Movement2 movement : movements){
            if(movement.move(board, initial, finalPosition) ) // && movement.checkMoveStrategies(board,initial, finalPosition)
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

    public List<ComposeMovement> getMovements() {
        return this.movements;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}


