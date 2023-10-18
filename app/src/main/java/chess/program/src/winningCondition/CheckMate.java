package chess.program.src.winningCondition;

import chess.program.src.Board;
import chess.program.src.Piece;
import chess.program.src.Position;
import chess.program.src.boardValidator.Validator;
import chess.program.src.enums.Color;

import java.util.List;

public class CheckMate implements WinCondition{
    private Validator checkValidator;
    private List<Color> colors;
    @Override
    public boolean winCondition(Board board, Position initial, Position finalPosition) {
        Color color = board.getPiece(initial).getColor();
        for (Color color1 : colors) {
            if (color != color1) {
                Position kingPosition = board.getKingPosition(color1);
                Piece king = board.getPiece(kingPosition);
                Board board1 = board.copy();
                if (!checkValidator.validate(initial, finalPosition, board1)) {
                    for (int i = 1; i <= 8; i++) {
                        for (int j = 1; j <= 8; j++) {
                            Position position = new Position(i, j);
                            if (king.moveValidation(kingPosition, position, board1)) {
                                if (checkValidator.validate(kingPosition, position, board1)) {
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                }
                return false;
            }
        }
        return false;
    }

}
