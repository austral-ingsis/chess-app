package chess.program.src.winningCondition;

import chess.program.src.Board;
import chess.program.src.Piece;
import chess.program.src.Position;
import chess.program.src.boardValidator.CheckValidator;
import chess.program.src.boardValidator.Validator;
import chess.program.src.enums.Color;

import java.util.List;

public class CheckMate implements WinCondition {
    private Validator checkValidator = new CheckValidator();
    private List<Color> colors = List.of(Color.WHITE, Color.BLACK);
    @Override
    public boolean winCondition(Board board, Position initial, Position finalPosition) {
        return true;
    }




}
