package chess.program.src.main;

import common.Board;
import common.Piece;
import common.Position;
import common.boardValidator.CheckValidator;
import common.boardValidator.Validator;
import common.enums.Color;
import common.enums.Type;
import common.winningCondition.WinCondition;

import java.util.ArrayList;
import java.util.List;

public class CheckMate implements WinCondition {
    private Validator checkValidator = new CheckValidator();
    private Color color ;
    @Override
    public boolean winCondition(Board board, Position initial, Position finalPosition) {
        Piece piece = board.getPiece(finalPosition);
        if (piece.getColor() == Color.WHITE) {color = Color.BLACK;}
        else {color = Color.WHITE;}


        List<Position> positions = board.getAllPositions();
        List<Position> colorInCheckPositions = getPositionsByPieceColor(board, positions, color);
        for (Position position : colorInCheckPositions) {
            Piece piece1 = board.getPiece(position);
                    for (Position position1 : positions) {
                        if (position != position1) {
                            Board board1 = board.copy();
                            if (piece1.moveValidation(position, position1, board1)) {
                                if(position1 != board1.getKingPosition(color)){
                                   if (checkValidator.validate(position, position1, board1)) {
                                   if(piece1.getType() != Type.KING && piece1.getType() != Type.FRSTKING){

                                            return false;
                                    }else{
                                        if(checkValidator.validate(position,finalPosition,board1)){return false;}
                                        if (checkValidator.validate(position, position, board1)) {return false;}
                              }
                            }
                        }}
                    }}

        }
        return true;
    }



    private List<Position> getPositionsByPieceColor(Board board, List<Position> pos, Color color) {
        List<Position> positions = new ArrayList<>();
        for (Position position : pos) {
            Piece piece = board.getPiece(position);
            if (piece != null) {
                if (piece.getColor() == color) {
                    positions.add(position);
                }
            }
        }
        return positions;
    }


}
