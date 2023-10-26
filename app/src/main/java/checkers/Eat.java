package checkers;

import chess.program.src.boardMovement.BoardMovement;
import common.Board;
import common.BoardResult;
import common.Piece;
import common.Position;

public class Eat implements BoardMovement {
    @Override
    public BoardResult move(Board board, Position inicial, Position finalPosition) {
        Piece piece = board.getPiece(inicial);
        if(piece == null){return new BoardResult(board,false);}

        int desplazamientox = finalPosition.getRow() - inicial.getRow();
        int desplazamientoy = finalPosition.getColumn() - inicial.getColumn();
        Board board1 = board.copy();
        if(Math.abs(desplazamientox) == 2){
            return handleEatMovement(board1,desplazamientox,desplazamientoy,finalPosition,inicial,piece);
        }

    return new BoardResult(board,false);
    }


    private BoardResult handleEatMovement(Board board1, int desplazamientox, int desplazamientoy, Position finalPosition, Position inicial, Piece piece){
        if (desplazamientox > 0 && desplazamientoy > 0){ board1.put(new Position(inicial.getRow()+1,inicial.getColumn()+1),null);board1.put(finalPosition,piece);}
        if (desplazamientox > 0 && desplazamientoy < 0){ board1.put(new Position(inicial.getRow()+1,inicial.getColumn()-1),null);board1.put(finalPosition,piece);}
        if (desplazamientox < 0 && desplazamientoy > 0){ board1.put(new Position(inicial.getRow()-1,inicial.getColumn()+1),null);board1.put(finalPosition,piece);}
        if (desplazamientox < 0 && desplazamientoy < 0){ board1.put(new Position(inicial.getRow()-1,inicial.getColumn()-1),null);board1.put(finalPosition,piece);}
        board1.put(inicial,null);
        return new BoardResult(board1,true);
    }


}
