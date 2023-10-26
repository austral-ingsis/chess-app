package common.movement2;

import common.Board;
import common.Piece;
import common.Position;
import common.movement2.strategyMovement.HorizontalTrepassinValidator;
import common.movement2.strategyMovement.ValidateMovement;

import java.util.ArrayList;
import java.util.List;

public class HorizontalAndVerticalMovement implements Movement2 {

    private int left;
    private int right;
    private int foward;
    private int backwards;
    private List<ValidateMovement> movValidators;

    public HorizontalAndVerticalMovement(int left1, int right1, int foward1, int backwards1, List<ValidateMovement> movs) {
        this.left = left1;
        this.right = right1;
        this.foward = foward1;
        this.backwards = backwards1;
        this.movValidators = movs;
    }

    public HorizontalAndVerticalMovement(int left1, int right1, int foward1, int backwards1) {
        this.left = left1;
        this.right = right1;
        this.foward = foward1;
        this.backwards = backwards1;
        this.movValidators = new ArrayList<>();
        movValidators.add(new HorizontalTrepassinValidator());
    }


    @Override
    public boolean move(Position inicial, Position finalPosition) {



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


    @Override
    public boolean checkMoveStrategies(Board board, Position inicial, Position finalPosition){
        for(ValidateMovement mov : movValidators){
            if(!mov.validate( inicial, finalPosition, board)){
            return false;
        }}
        return true;
    }


}
