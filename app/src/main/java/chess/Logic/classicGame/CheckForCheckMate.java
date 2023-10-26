package chess.Logic.classicGame;

import chess.Logic.MoveValidation;
import chess.Logic.PossibleMovements;
import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.Piece;
import chess.Models.SideColor;

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
