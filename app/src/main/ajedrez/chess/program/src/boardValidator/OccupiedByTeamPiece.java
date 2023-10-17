package chess.program.src.boardValidator;

import chess.program.src.Board;
import chess.program.src.Position;
import chess.program.src.enums.Color;
import chess.program.src.enums.Type;

public class OccupiedByTeamPiece implements Validator{
    @Override
    public boolean validate(Position initial, Position finalPosition, Board board) {
        Color color = board.getPiece(initial).getColor();
        if(board.getPiece(finalPosition) == null){return true;}
        return (board.getPiece(finalPosition).getColor() != color);
    }
}
