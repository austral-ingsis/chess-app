package chess.program.src.boardValidator;

import chess.program.src.Board;
import chess.program.src.Piece;
import chess.program.src.Position;
import chess.program.src.enums.Type;


public class CheckValidator implements Validator{

    @Override
    public boolean validate(Position initial, Position finalPosition, Board board) {
        Piece piece = board.getPiece(initial);
        Board board1 = board.copy();
        if(piece.getType() != Type.FRSTKING && piece.getType() != Type.KING){
            board1.put(finalPosition, piece);
            board1.put(initial, null);
            Position kingPosition = board1.getKingPosition(piece.getColor());
            return checkChecker(board1, piece, kingPosition);}
        else{
            if(Math.abs(finalPosition.getColumn()-initial.getColumn()) == 2){
            int x = initial.getRow();
            int y = (initial.getColumn() + finalPosition.getColumn())/2;
            Position middlePosition = new Position(x,y);
            return checkChecker(board1, piece, initial) &&
                   checkChecker(board1, piece, finalPosition) &&
                   checkChecker(board1, piece, middlePosition);
            }
            return checkChecker(board1, piece, finalPosition);
        }

    }


    private boolean checkChecker(Board board1, Piece piece, Position kingPosition){
        for(int i = 1; i <= 8; i++){
            for(int j = 1; j <= 8; j++){
                Position position = new Position(i,j);
                if(board1.getPiece(position) != null){
                    if(board1.getPiece(position).getColor() != piece.getColor()){
                        if(board1.getPiece(position).moveValidation(position, kingPosition, board1)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
