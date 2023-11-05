package common.movement2.strategyMovement;

import common.Board;
import common.Position;
import common.movement2.Movement2;

public class DiagonalMovement implements Movement2 {
    private int left;
    private int right;
    private int foward;
    private int backwards;


    public DiagonalMovement(int left1, int right1, int foward1, int backwards1){
        this.left = left1;
        this.right = right1;
        this.foward = foward1;
        this.backwards = backwards1;
    }

    @Override
    public boolean move(Board board, Position inicial, Position finalPosition) {
        int deltaRow = finalPosition.getRow() - inicial.getRow();
        int y = finalPosition.getColumn() - inicial.getColumn();
        if (Math.abs(deltaRow) == Math.abs(y)){
            if (y >= -left && y <= right){
                if (canMoveForward(deltaRow) && deltaRow >= -backwards){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canMoveForward(int deltaRow) {
        return deltaRow <= foward;
    }








}
