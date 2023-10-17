package chess.program.src;

import chess.program.src.enums.Color;
import chess.program.src.enums.Type;

import java.util.HashMap;
import java.util.Map;

public class Board {

private Map<Position,Piece> casilleros;
private int row ;
private int column ;

public Board(Map<Position,Piece> casilleros1) {
    casilleros = casilleros1;
}

public Board(Map<Position,Piece> casilleros1, int row1, int column1) {
        casilleros = casilleros1;
        row = row1;
        column = column1;
    }


    boolean mover(Position initial, Position finalPosition) {
        Piece piece = casilleros.get(initial);
        if(piece.moveValidation(initial,finalPosition,this)){
            return true;
        }
        return false;
    }
    public Piece getPiece(Position position){
        return casilleros.get(position);
    }

    public void put(Position position, Piece piece){
        casilleros.put(position, piece);
    }

    public Position getKingPosition(Color color) {
        for (Map.Entry<Position, Piece> entry : casilleros.entrySet()) {
            if(entry.getValue() != null){
            if ((entry.getValue().getType() == Type.KING || entry.getValue().getType() == Type.FRSTKING) && entry.getValue().getColor() == color) {
                return entry.getKey();
            }}
        }
        return null;
    }

    public Board copy(){
    Map<Position, Piece> copiedMap = new HashMap<>(casilleros);
    return new Board(copiedMap);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
