package common.boardMovement.replacement;

import common.boardMovement.BoardMovement;
import common.enums.Color;
import common.enums.Type;
import common.*;

public class ReplacementMovement implements BoardMovement {
    private Type type;
    private Piece piece;
    private Color color;
    private ReplacementStrategy strategy;

    public ReplacementMovement(Type type,Piece piece, ReplacementStrategy strategy) {
        this.type = type;
        this.piece = piece;
        this.color = piece.getColor();
        this.strategy = strategy;
    }

    @Override
    public BoardResult move(Board board, Position initial, Position finalPosition) {
        Piece piece = board.getPiece(initial);
        if (piece.getType() == this.type && piece.getColor() == this.color){
        if(strategy.replace(board,initial,finalPosition)){
            String id = piece.getId();
            this.piece.setId(id);
            Board board1 = board.copy();
            board1.put(finalPosition, this.piece);
            board1.put(initial, null);
            this.piece = new PieceImpl(this.piece);
            return new BoardResult(board1, true);
                }}
        return new BoardResult(board, false);
    }




}
