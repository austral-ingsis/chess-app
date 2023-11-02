package common.movement2;

import common.Board;
import common.Piece;
import common.Position;
import common.movement2.strategyMovement.DiagonalTrepassinStrategy;
import common.movement2.strategyMovement.ValidateMovement;

import java.util.ArrayList;
import java.util.List;

public class DiagonalMovement implements Movement2{
    private int left;
    private int right;
    private int foward;
    private int backwards;
    private List<ValidateMovement> movValidators;


    public DiagonalMovement(int left1, int right1, int foward1, int backwards1, List<ValidateMovement> movs){
        this.left = left1;
        this.right = right1;
        this.foward = foward1;
        this.backwards = backwards1;
        this.movValidators = movs;
    }

    public DiagonalMovement(int left1, int right1, int foward1, int backwards1){
        this.left = left1;
        this.right = right1;
        this.foward = foward1;
        this.backwards = backwards1;
        this.movValidators = new ArrayList<>();
        movValidators.add(new DiagonalTrepassinStrategy());
    }

    @Override
    public boolean move(Board board, Position inicial, Position finalPosition) {
        if(!checkMoveStrategies(board, inicial, finalPosition)){return false;}
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


   private boolean checkMoveStrategies(Board board, Position inicial, Position finalPosition){
        for(ValidateMovement mov : movValidators){
            if(!mov.validate( inicial, finalPosition, board)){
                return false;
            }}
        return true;
    }





}
