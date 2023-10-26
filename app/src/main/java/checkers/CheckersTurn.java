package checkers;

import common.*;
import common.enums.Color;
import common.movement2.*;
import common.movement2.strategyMovement.*;
import common.turn.Turn;

import java.util.ArrayList;
import java.util.List;

public class CheckersTurn implements Turn {

    private Board board;


    public CheckersTurn(Board board){
        this.board = board;
    }
    @Override
    public Player isTurn(List<Player> players, Position initial, Position finalPosition) {
        Player player = players.get(0);
        if (isThereAnyPieceToEat(player.getColor(),initial,finalPosition)){
            return player;
        }
        else{
            players.remove(0);
            players.add(player);
            return player;}
    }

    private boolean isThereAnyPieceToEat(Color color, Position initial, Position finalPosition) {
        int deltaRow = Math.abs(finalPosition.getRow() - initial.getRow());
        if(deltaRow == 1){return false;}
        Board board1 = board.copy();
        Piece piece = board1.getPiece(initial);
        board1.put(finalPosition,piece);
        removeEatenPiece(board1,initial,finalPosition);
        board1.put(initial,null);


        Movement2 eatMovement = getEatMovements(color);

        List<Position> positions = board.getAllPositions();
        List<Position> positions1 = positions;


        for (Position position : positions) {
            for (Position position1 : positions1) {
                if (position1 != position) {
                    Piece piece1 = board1.getPiece(position);
                    if(piece1 != null)
                        if (piece1.getColor() == color && eatMovement.move(position, position1) && eatMovement.checkMoveStrategies(board1, position, position1)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }



    private Movement2 getEatMovements(Color color){
        List<ValidateMovement> validateMovements = new ArrayList<>();
        validateMovements.add(new NoEatStrategy());
        validateMovements.add(new DiagonalObligatoryTrepassin());
        if (color == Color.WHITE){
            return new DiagonalMovement(2, 2, 2, 0, validateMovements);
        }
        else{
            return new DiagonalMovement(2, 2, 0, 2, validateMovements);
        }
    }


    private void removeEatenPiece(Board board1, Position inicial, Position finalPosition){
        int desplazamientox = finalPosition.getRow() - inicial.getRow();
        int desplazamientoy = finalPosition.getColumn() - inicial.getColumn();
        if (desplazamientox > 0 && desplazamientoy > 0){ board1.put(new Position(inicial.getRow()+1,inicial.getColumn()+1),null);}
        if (desplazamientox > 0 && desplazamientoy < 0){ board1.put(new Position(inicial.getRow()+1,inicial.getColumn()-1),null);}
        if (desplazamientox < 0 && desplazamientoy > 0){ board1.put(new Position(inicial.getRow()-1,inicial.getColumn()+1),null);}
        if (desplazamientox < 0 && desplazamientoy < 0){ board1.put(new Position(inicial.getRow()-1,inicial.getColumn()-1),null);}
    }

}
