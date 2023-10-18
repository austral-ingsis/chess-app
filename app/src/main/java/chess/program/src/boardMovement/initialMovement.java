package chess.program.src.boardMovement;

import chess.program.src.*;
import chess.program.src.enums.Color;
import chess.program.src.enums.Type;

public class initialMovement implements BoardMovement{
    private Type type;
    private Piece piece;
    private Color color;

    public initialMovement(Type type,Piece piece) {
        this.type = type;
        this.piece = piece;
        this.color = piece.getColor();
    }

    @Override
    public BoardResult move(Board board, Position initial, Position finalPosition) {
        Piece piece = board.getPiece(initial);
        if (piece.getType() == this.type && piece.getColor() == this.color){
            String id = piece.getId();
            this.piece.setId(id);
            Board board1 = board.copy();
            board1.put(finalPosition, this.piece);
            board1.put(initial, null);
            this.piece = new PieceImpl(this.piece);
            return new BoardResult(board1, true);
                }
        return new BoardResult(board, false);
    }




}
