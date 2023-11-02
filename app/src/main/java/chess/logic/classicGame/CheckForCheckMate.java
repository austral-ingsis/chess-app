package chess.logic.classicGame;

import chess.logic.MoveValidation;
import chess.logic.PossibleMovements;
import common.models.Board;
import common.models.Coordinate;
import common.models.Piece;
import common.models.SideColor;

import java.util.List;

public class CheckForCheckMate {
        private final MoveValidation moveValidation = new MoveValidation();
        private final PossibleMovements possibleMovements = new PossibleMovements();
        public Boolean check(Board board, SideColor color, List<Piece> pieces, CheckForCheck checkForCheck){
            SideColor oppositeColor = color == SideColor.White ? SideColor.Black : SideColor.White;
            for (Piece piece : pieces) {
                if (piece.getColor() == oppositeColor) {
                    if (board.getSquareOfPiece(piece).successfulResult().isEmpty()) {
                        continue;
                    }
                    Coordinate initialSquare = board.getSquareOfPiece(piece).successfulResult().get();
                    List<Coordinate> possibleMoves = possibleMovements.getPossibleMovements(board,piece, initialSquare);
                    for (Coordinate possibleMove : possibleMoves) {
                        if(!moveValidation.validateMove(piece, board,possibleMove, initialSquare)){
                            continue;
                        }
                        Board newBoard = board.positionPiece(piece, possibleMove);
                        if(!checkForCheck.check(newBoard,oppositeColor, piece, possibleMove)){
                            return false;
                        }
                    }
                }
            }
            return true;
        }
}
