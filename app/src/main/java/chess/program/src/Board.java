package chess.program.src;

import chess.program.src.enums.Color;
import chess.program.src.enums.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

private Map<Position,Piece> casilleros;
private int row ;
private int column ;


public Board(Map<Position,Piece> casilleros1, int row1, int column1) {
        casilleros = casilleros1;
        row = row1;
        column = column1;
        for(int i = 1; i <= row; i++){
            for(int j = 1; j <= column; j++){
                Position position = new Position(i,j);
                casilleros.putIfAbsent(position, null);
        }
        }
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
        List<Position> positions = this.getAllPositions();
        for (Position position : positions) {
            Piece piece = this.getPiece(position);
            if (piece != null) {
                if (piece.getColor() == color) {
                    if (piece.getType() == Type.KING || piece.getType() == Type.FRSTKING) {
                        return position;
                    }
                }
            }
        }
        return null;
    }

    public Board copy(){
    Map<Position, Piece> copiedMap = new HashMap<>(casilleros);
    return new Board(copiedMap,this.row,this.column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public List<Position> getAllPositions(){
        return casilleros.keySet().stream().toList();
    }

}
