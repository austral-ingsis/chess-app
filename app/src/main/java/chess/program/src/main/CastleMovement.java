package chess.program.src.main;

import common.boardMovement.BoardMovement;
import common.enums.Type;
import common.movement2.ComposeMovement;
import common.movement2.strategyMovement.DiagonalMovement;
import common.movement2.strategyMovement.HorizontalAndVerticalMovement;
import common.movement2.Movement2;
import common.*;

import java.util.List;

public class CastleMovement implements BoardMovement {


    @Override
    public BoardResult move(Board board, Position inicial, Position finalPosition) {
        int y = finalPosition.getColumn() - inicial.getColumn();
        Piece king = board.getPiece(inicial);

        if (king.getType() == Type.FRSTKING && Math.abs(y) == 2) {
            Board board1 = board.copy();
            if (y > 0) {
                Board board2 = castleKing(board1, inicial, finalPosition, 1);
                return new BoardResult(board2, true);
            } else {
                Board board2 = castleKing(board1, inicial, finalPosition, -2);
                return new BoardResult(board2, true);
            }
        }

        return new BoardResult(board, false);
    }




    private Board castleKing(Board board, Position inicial, Position finalPosition, int direction) {
        Position towerPosition = new Position(inicial.getRow(), finalPosition.getColumn() + direction);
        Piece tower = board.getPiece(towerPosition);

        if (tower.getType() == Type.FIRSTTOWER) {
            Piece newKing = makeKing(board.getPiece(inicial));

            board.put(finalPosition, newKing);
            if ( direction > 0){
                Position position = new Position(inicial.getRow(), finalPosition.getColumn() - 1);
                board.put(position,  makeTower(tower));
                board.put(towerPosition, null);
                board.put(inicial, null);
            }
            else {
                Position position = new Position(inicial.getRow(), finalPosition.getColumn() + 1);
                board.put(position, makeTower(tower));
                board.put(towerPosition, null);
                board.put(inicial, null);
            }
        }

        return board;
    }

    private Piece makeKing(Piece king) {
        Movement2 movement2 = new HorizontalAndVerticalMovement(1, 1, 1, 1);
        Movement2 movement21 = new DiagonalMovement(1, 1, 1, 1);
        List<Movement2> movements = new java.util.ArrayList<>();
        movements.add(movement2);
        movements.add(movement21);
        ComposeMovement composeMovement = new ComposeMovement(movements);

        Piece king1 = new PieceImpl(king.getColor(), Type.KING, List.of(composeMovement));
        king1.setId(king.getId());
        return king1;
    }

    private Piece makeTower(Piece tower) {
        Movement2 movement2 = new HorizontalAndVerticalMovement(8, 8, 8, 8);
        List<Movement2> movements = new java.util.ArrayList<>();
        movements.add(movement2);
        ComposeMovement composeMovement = new ComposeMovement(movements);

        Piece tower1 = new PieceImpl(tower.getColor(), Type.TOWER, List.of(composeMovement));
        tower1.setId(tower.getId());
        return tower1;
    }



}
