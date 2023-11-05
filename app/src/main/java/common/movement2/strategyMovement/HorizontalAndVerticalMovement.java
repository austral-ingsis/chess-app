package common.movement2.strategyMovement;

import common.Board;
import common.Position;
import common.movement2.Movement2;

public class HorizontalAndVerticalMovement implements Movement2 {

    private int left;
    private int right;
    private int foward;
    private int backwards;

    public HorizontalAndVerticalMovement(int left1, int right1, int foward1, int backwards1) {
        this.left = left1;
        this.right = right1;
        this.foward = foward1;
        this.backwards = backwards1;
    }

    @Override
    public boolean move(Board board,Position inicial, Position finalPosition) {


        int x = finalPosition.getRow() - inicial.getRow();
        int y = finalPosition.getColumn() - inicial.getColumn();
        if (x == 0 || y == 0) {
            if (y == 0) {
                return (x >= (-1 * backwards) && x <= foward);
            } else {
                return (y >= -left && y <= right);
            }
        }
        return false;
    }





}
