package chess.Models;

import chess.Moves.interfaces.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PieceBuilder {
    private int id = 0;
    private String pieceName;
    private List<Move> movements;
    private List<Move> eatMovements;
    private boolean isImportant;
    private final List<Piece> pieces = new ArrayList<>();

    public Piece createPiece(String pieceName,Coordinate coordinate, List<Move> movements, List<Move> eatMovements, boolean isImportant, SideColor color) {
        this.pieceName = pieceName;
        this.movements = movements;
        this.eatMovements = eatMovements;
        this.isImportant = isImportant;
        Piece newPiece = new Piece(pieceName, coordinate, movements, eatMovements, color, isImportant, id);
        pieces.add(newPiece);
        id ++;
        return newPiece;
    }

    public Piece createPiece( String pieceName,Coordinate coordinate, List<Move> movements, boolean isImportant, SideColor color) {
        this.pieceName = pieceName;
        this.movements = movements;
        this.eatMovements = movements;
        this.isImportant = isImportant;
        id ++;
        Piece newPiece = new Piece(pieceName, coordinate, movements, color, isImportant, id);
        pieces.add(newPiece);
        return newPiece;
    }
     public Piece clonePiece(String pieceName, Coordinate coordinate,SideColor color){
        for (Piece piece : pieces) {
            if (Objects.equals(piece.getName(), pieceName)){
                this.movements = piece.getMovements();
                this.eatMovements = piece.getEatMovements();
                this.isImportant = piece.isImportant();
                id++;
                return new Piece(pieceName, coordinate, movements, eatMovements, color, isImportant, id);
            }
        }
         return null;
     }

     public Piece createNullPiece(Coordinate coordinate){
        return new Piece("null", coordinate, new ArrayList<>(), new ArrayList<>(), SideColor.NULL, false, 0);
     }

}
