package chess.program.src.boardValidator;

import common.Board;
import common.Piece;
import common.Position;
import common.enums.Type;
import common.movement2.Movement2;

import java.util.List;

public class MoveIfEat implements Validator{

    private List<Type> type;
    private Movement2 movement2;

    public MoveIfEat(List<Type> type, Movement2 movement2) {
        this.type = type;
        this.movement2 = movement2;
    }
    @Override
    public boolean validate(Position initial, Position finalPosition, Board board) {
        Piece piece = board.getPiece(initial);
        for( Type type : type){
            if (piece.getType() == type){
                if (movement2.move(initial, finalPosition)) {
                    return board.getPiece(finalPosition) != null;
            }
        }}
        return true;
    }
}
