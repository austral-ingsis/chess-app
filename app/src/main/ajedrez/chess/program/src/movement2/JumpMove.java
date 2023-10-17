package chess.program.src.movement2;

import chess.program.src.Board;
import chess.program.src.Position;

public class JumpMove implements Movement2{
    private int left;
    private int right;
    private int foward;
    private int backwards;

    public JumpMove(int left1, int right1, int foward1, int backwards1){
        this.left = left1;
        this.right = right1;
        this.foward = foward1;
        this.backwards = backwards1;
    }


    @Override
    public boolean move(Position inicial, Position finalPosition) {
        int x = Math.abs(finalPosition.getRow() - inicial.getRow());
        int y = Math.abs(finalPosition.getColumn() - inicial.getColumn());
        if (y == left || y == right){
            if (x == foward || x == backwards){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validate(Position initial, Position finalPosition, Board board) {
        return true;
    }

}
