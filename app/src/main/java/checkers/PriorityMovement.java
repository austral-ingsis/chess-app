package checkers;

import common.boardValidator.Validator;
import common.Board;
import common.Piece;
import common.Position;
import common.enums.Color;
import common.movement2.Movement2;

import java.util.List;

public class PriorityMovement implements Validator {
    private Movement2 movement2;
    private Color color;

    public PriorityMovement(Movement2 movement2,Color color){this.movement2 = movement2;this.color = color;}


    @Override
    public boolean validate(Position initial, Position finalPosition, Board board) {
        Piece piece = board.getPiece(initial);
        if(piece.getColor() != color){return true;}
        if(movement2.move(board,initial,finalPosition)){// && movement2.checkMoveStrategies(board,initial,finalPosition)){
            return true;
        }
        else {
            return posiblePriorityMovement(board);
        }
    }


    private boolean posiblePriorityMovement( Board board){
        List<Position> positions = board.getAllPositions();
        List<Position> positions1 = positions;


        for (Position position : positions) {
            for (Position position1 : positions1) {
                if (position1 != position) {
                    Piece piece = board.getPiece(position);
                    if(piece != null)
                        if (piece.getColor() == this.color && movement2.move(board,position, position1)){// && movement2.checkMoveStrategies(board, position, position1)) {
                            return false;
                        }
                }
            }

        }
        return true;
    }







}
