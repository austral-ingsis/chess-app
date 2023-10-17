package chess.program.src.boardValidator;

import chess.program.src.Board;
import chess.program.src.Position;
import chess.program.src.enums.Color;
import chess.program.src.enums.Type;

import java.util.List;

public class cantBeEaten implements Validator{

    private List<Type> typesThatCantBeEaten;

    public cantBeEaten(List<Type> types){
        this.typesThatCantBeEaten = types;
    }

    @Override
    public boolean validate(Position initial, Position finalPosition, Board board) {
        if(board.getPiece(finalPosition) == null){return true;}
        for (Type type: typesThatCantBeEaten) {
            if(board.getPiece(finalPosition).getType() == type){
                return false;
            }}
        return true;
    }

}
