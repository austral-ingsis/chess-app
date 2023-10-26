package chess.Moves;

import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.SideColor;
import chess.Moves.interfaces.Move;

public class HorizontalMove implements Move {
    int columnsIncremented;
    private final boolean canJump;
    private boolean limitless;

    public HorizontalMove(int columnsIncremented, boolean canJump) {
        if (columnsIncremented == 0){
            limitless = true;
        }
        this.columnsIncremented = columnsIncremented;
        this.canJump = canJump;
    }
    public HorizontalMove(boolean canJump){
        limitless = true;
        this.canJump = canJump;
    }
    @Override
    public Boolean checkMove(Coordinate initialSquare, Coordinate finalSquare, Board board, SideColor side) {
        checkLimitless(board);
        if(finalSquare.column() > initialSquare.column() && !canJump){
            for(int i = 1; i < finalSquare.column() - initialSquare.column(); i++){
                Coordinate coordinate = new Coordinate(initialSquare.column() +i, initialSquare.row());
                if(board.checkForPieceInSquare(coordinate)){
                    return false;
                }
            }
            return finalSquare.row() == initialSquare.row();
        }
        else if(finalSquare.column() < initialSquare.column() && !canJump){
            for(int i = 1; i < initialSquare.column() - finalSquare.column(); i++){
                Coordinate coordinate = new Coordinate(initialSquare.column() -i, initialSquare.row());
                if(board.checkForPieceInSquare(coordinate)){
                    return false;
                }
            }
            return finalSquare.row() == initialSquare.row();
        }
        return false;
    }


    private void checkLimitless(Board board) {
        if (limitless){
            columnsIncremented = board.getColumns();
        }
    }
}
