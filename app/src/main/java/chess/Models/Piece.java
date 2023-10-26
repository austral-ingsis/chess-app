package chess.Models;

import chess.Logic.*;
import chess.Logic.interfaces.CheckLegalMove;
import chess.Logic.interfaces.WinCondition;
import chess.Moves.interfaces.Move;
import chess.Results.MoveResults;

import java.util.List;
import java.util.Objects;

public class Piece{
    private final String pieceName;
    private final Coordinate initialSquare;
    private final List<Move> movements;
    private final List<Move> eatMovements;
    private final SideColor color;
    private final Boolean isImportant;
    private final int id;

    public Piece(String pieceName, Coordinate initialSquare, List<Move> movements, List<Move> eatMovements, SideColor color, boolean isImportant, int id) {
        this.pieceName = pieceName;
        this.initialSquare = initialSquare;
        this.color = color;
        this.movements = movements;
        this.eatMovements = eatMovements;
        this.isImportant = isImportant;
        this.id = id;
    }

    public Piece(String pieceName, Coordinate initialSquare, List<Move> movements, SideColor color, boolean isImportant, int id) {
        this.pieceName = pieceName;
        this.initialSquare = initialSquare;
        this.color = color;
        this.movements = movements;
        this.eatMovements = movements;
        this.isImportant = isImportant;
        this.id = id;
    }

    public MoveResults<Board, Boolean> movePiece(Coordinate initial, Coordinate toSquare, Board board, WinCondition winCondition, CheckLegalMove checkLegalMove) {
        if (!CommonRule.checkRule(board, this, toSquare)) {
            return new MoveResults<>(board, true, "Common Rule unfollowed");
        }
        if (!Objects.equals(board.getSquare(toSquare).getPiece().getName(), "null")) {
            return checkLegalMove.check(this,toSquare, board, initial, eatMovements,winCondition);
        } else {
            return checkLegalMove.check(this,toSquare, board, initial, movements,winCondition);
        }
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

}
