package chess.Models;

import chess.Logic.*;
import chess.Results.MoveResults;

import java.util.List;

public class Piece{
    private final String pieceName;
    private final Coordinate initialSquare;
    private final List<Move> movements;
    private final List<Move> eatMovements;
    private final SideColor color;
    private final Boolean isImportant;
    private final String pieceId;
    private final int id;
    private final CheckLegalMove checkLegalMove = new CheckLegalMove();

    public Piece(String pieceName, Coordinate initialSquare, List<Move> movements, List<Move> eatMovements, SideColor color, boolean isImportant, String pieceId, int id) {
        this.pieceName = pieceName;
        this.initialSquare = initialSquare;
        this.color = color;
        this.movements = movements;
        this.eatMovements = eatMovements;
        this.isImportant = isImportant;
        this.pieceId = pieceId;
        this.id = id;
    }

    public Piece(String pieceName, Coordinate initialSquare, List<Move> movements, SideColor color, boolean isImportant, String pieceId, int id) {
        this.pieceName = pieceName;
        this.initialSquare = initialSquare;
        this.color = color;
        this.movements = movements;
        this.eatMovements = movements;
        this.isImportant = isImportant;
        this.pieceId = pieceId;
        this.id = id;
    }

    public MoveResults<Board, Boolean> movePiece(Coordinate toSquare, Board board) {
        Coordinate initial = board.getSquareOfPiece(this).successfulResult().get();
        if (!CommonRule.checkRule(board, this, toSquare)) {
            return new MoveResults<>(board, true, "Common Rule unfollowed");
        }
        if (board.getSquare(toSquare).getPiece() != null) {
            return checkLegalMove.check(this,toSquare, board, initial, eatMovements);
        } else {
            return checkLegalMove.check(this,toSquare, board, initial, movements);
        }
    }

    public Boolean checkForCheck(Coordinate toPosition, Board board) {
        if (board.getSquareOfPiece(this).successfulResult().isEmpty())
            return false;
        for (Move move : eatMovements) {
            if (move.checkMove(board.getSquareOfPiece(this).successfulResult().get(), toPosition, board, this.getColor())) {
                return true;
            }
        }
        return false;
    }
    public String getName() {
        return pieceName;
    }

    public Coordinate getInitialSquare() {
        return initialSquare;
    }

    public Boolean isImportant() {
        return isImportant;
    }

    public SideColor getColor() {
        return color;
    }

    public List<Move> getEatMovements() {
        return eatMovements;
    }

    public List<Move> getMovements() {
        return movements;
    }

    public int getId() {
        return id;
    }

    public String getPieceId() {
        return pieceId;
    }
    public String getPieceName(){
        return pieceName;}
}
