package checkers;

import common.boardMovement.BoardMovement;
import common.Board;
import common.BoardResult;
import common.Piece;
import common.Position;
import common.boardMovement.replacement.PositionReplacementStrategy;
import common.boardMovement.replacement.ReplacementMovement;
import common.enums.Color;
import common.enums.Type;
import common.PieceImpl;
import common.movement2.ComposeMovement;
import common.movement2.strategyMovement.DiagonalMovement;
import common.movement2.strategyMovement.DiagonalObligatoryTrepassin;
import common.movement2.strategyMovement.NoEatStrategy;

import java.util.List;

public class Eat implements BoardMovement {
    private Piece whiteQueen = new PieceImpl(Color.WHITE, Type.QUEEN, List.of(new ComposeMovement(List.of(new DiagonalMovement(2, 2, 2, 2),new DiagonalObligatoryTrepassin(),new NoEatStrategy())),new ComposeMovement(List.of(new DiagonalMovement(1,1,1,1),new NoEatStrategy()))));
    private Piece blackQueen = new PieceImpl(Color.BLACK, Type.QUEEN, List.of(new ComposeMovement(List.of(new DiagonalMovement(2, 2, 2, 2), new DiagonalObligatoryTrepassin(),new NoEatStrategy())),new ComposeMovement(List.of(new DiagonalMovement(1,1,1,1),new NoEatStrategy()))));
    private BoardMovement whiteCoronation = new ReplacementMovement(Type.KNIGHT, whiteQueen, new PositionReplacementStrategy(List.of(new Position(8, 1),new Position(8, 2),new Position(8, 3),new Position(8, 4),new Position(8, 5),new Position(8, 6),new Position(8, 7),new Position(8, 8))));
    private BoardMovement blackCoronation = new ReplacementMovement(Type.KNIGHT, blackQueen, new PositionReplacementStrategy(List.of(new Position(1, 1),new Position(1, 2),new Position(1, 3),new Position(1, 4),new Position(1, 5),new Position(1, 6),new Position(1, 7),new Position(1, 8))));

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
        BoardResult temporalBoardResult = null;
        if (piece.getColor() == Color.WHITE && finalPosition.getRow() == 8){
            temporalBoardResult = whiteCoronation.move(board1,inicial,finalPosition);
        }
        if (piece.getColor() == Color.BLACK && finalPosition.getRow() == 1){
            temporalBoardResult = blackCoronation.move(board1,inicial,finalPosition);
        }

        if(temporalBoardResult == null){
        if (desplazamientox > 0 && desplazamientoy > 0){ board1.put(new Position(inicial.getRow()+1,inicial.getColumn()+1),null);board1.put(finalPosition,piece);}
        if (desplazamientox > 0 && desplazamientoy < 0){ board1.put(new Position(inicial.getRow()+1,inicial.getColumn()-1),null);board1.put(finalPosition,piece);}
        if (desplazamientox < 0 && desplazamientoy > 0){ board1.put(new Position(inicial.getRow()-1,inicial.getColumn()+1),null);board1.put(finalPosition,piece);}
        if (desplazamientox < 0 && desplazamientoy < 0){ board1.put(new Position(inicial.getRow()-1,inicial.getColumn()-1),null);board1.put(finalPosition,piece);}
        board1.put(inicial,null);
        return new BoardResult(board1,true);
        }
        else{
            if (desplazamientox > 0 && desplazamientoy > 0){ temporalBoardResult.getBoardResult().put(new Position(inicial.getRow()+1,inicial.getColumn()+1),null);}
            if (desplazamientox > 0 && desplazamientoy < 0){ temporalBoardResult.getBoardResult().put(new Position(inicial.getRow()+1,inicial.getColumn()-1),null);}
            if (desplazamientox < 0 && desplazamientoy > 0){ temporalBoardResult.getBoardResult().put(new Position(inicial.getRow()-1,inicial.getColumn()+1),null);}
            if (desplazamientox < 0 && desplazamientoy < 0){ temporalBoardResult.getBoardResult().put(new Position(inicial.getRow()-1,inicial.getColumn()-1),null);}
            return temporalBoardResult;
        }


    }


}
