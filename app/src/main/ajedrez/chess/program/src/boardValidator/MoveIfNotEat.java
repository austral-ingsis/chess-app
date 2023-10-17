package chess.program.src.boardValidator;

import chess.program.src.Board;
import chess.program.src.Piece;
import chess.program.src.Position;
import chess.program.src.enums.Type;
import chess.program.src.movement2.Movement2;

import java.util.List;

public class MoveIfNotEat implements Validator{

    private List<Type> type;
    private Movement2 movement2;

    public MoveIfNotEat(List<Type> type, Movement2 movement2) {
        this.type = type;
        this.movement2 = movement2;
    }
    @Override
    public boolean validate(Position initial, Position finalPosition, Board board) {
        Piece piece = board.getPiece(initial);
        for( Type type : type){
            if (piece.getType() == type){
                if (movement2.move(initial, finalPosition)) {
                    return board.getPiece(finalPosition) == null;
                }
            }}
        return true;
    }
}
