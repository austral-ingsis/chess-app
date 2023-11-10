package chess.logic.classicGame;

import common.models.*;
import common.moves.Move;
import common.results.CheckResult;
import common.results.MoveResults;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PieceMover {
    PieceBuilder pieceBuilder = new PieceBuilder();
    public MoveResults<Board,Boolean> check(Board board, Coordinate initial, Coordinate toSquare, List<Move> movements, Piece pieceMoving, Piece pieceEaten){
        for (Move move : movements) {
            CheckResult<Coordinate,Boolean> checkResult = move.checkMove(initial, toSquare, board, pieceMoving.getColor());
            if (checkResult.outputResult()) {
                Optional<Coordinate> coordinateEaten = board.getSquareOfPiece(pieceEaten).successfulResult();
                Board b = board.positionPiece(pieceBuilder.createNullPiece(coordinateEaten.get()), coordinateEaten.get());
                Board newBoard = b.positionPiece(pieceMoving,checkResult.successfulResult());
                Board newBoard2 = newBoard.positionPiece(pieceBuilder.createNullPiece(initial), initial);
                List<MovementHistory> newMovement = new ArrayList<>(board.getMovements());
                MovementHistory movement = new MovementHistory(initial, checkResult.successfulResult(), pieceMoving);
                newMovement.add(movement);

                Board boa = new Board(board.getRows(), board.getColumns(), newBoard2.getPieces(), newBoard2.getSquares(), newMovement);
                return new MoveResults<>(boa, false, "Piece Moved");
            }
        }
        return new MoveResults<>(board, true, "Piece not moved");
    }
}
