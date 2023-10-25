package chess.Models;

import chess.Results.GetResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Board {
    private final Square[] squares;
    private final List<Piece> pieces;
    private final int column;
    private final int row;
    private final List<MovementHistory> movements;

    public Board(int row, int column, List<Piece> blackPieces, List<Piece> whitePieces) {
        squares = new Square[row * column];
        whitePieces.addAll(blackPieces);
        this.pieces = whitePieces;
        this.column = column;
        this.row = row;
        this.movements = new ArrayList<>();
        for (int i = 0; i < row * column; i++) {
            squares[i] = new Square(new Coordinate(i % row, i /row), null);
        }
        for (Piece piece : whitePieces) {
            Square square = squares[piece.getInitialSquare().column() + piece.getInitialSquare().row() * row];
            square.setPiece(piece);
        }
        System.out.println("Java.Objects.Board Created with: " + row + "*" + column);
    }
    public Board(int row, int column, List<Piece> pieces, Square[] squares, List<MovementHistory> movements) {
        this.row = row;
        this.column = column;
        this.squares = squares;
        this.pieces = pieces;
        this.movements = new ArrayList<>(movements);
    }

    public Board positionPiece(Piece piece, Coordinate position) {
        Square square = squares[position.column() + position.row() * row];
        square = new Square(square.getCoordinate(), piece);
        Square[] newSquares = this.squares.clone();
        newSquares[position.column() + position.row() * row] = square;
        return new Board(row,column,this.pieces,newSquares,this.getMovements());
    }

    public GetResult<Coordinate,Boolean> getSquareOfPiece(Piece piece) {
        for (Square square : squares) {
            Piece squarePiece = square.getPiece();
            if (squarePiece != null && Objects.equals(squarePiece.getId(), piece.getId())) {
                return new GetResult<>(Optional.of(square.getCoordinate()),false);
            }
        }
        return new GetResult<>(Optional.empty(),true);
    }

    public GetResult<Piece,Boolean> findImportantPiece(SideColor color){
        for(Piece p : pieces){
            if(p.isImportant() && p.getColor() == color){
                return new GetResult<>(Optional.of(p),false);
            }
        }
        return new GetResult<>(Optional.empty(),true);
    }

    public Square getSquare(Coordinate coordinate) {
        return squares[coordinate.column() + coordinate.row() * this.row];
    }


    public GetResult<Piece,Boolean> getPiece(String name) {
        for (Piece piece : pieces) {
            if (Objects.equals(piece.getPieceId(), name)) {
                return new GetResult<>(Optional.of(piece),false);
            }
        }
        return new GetResult<>(Optional.empty(),true);
    }

    public int getRows() {
        return row;
    }

    public boolean checkForPieceInSquare(Coordinate coordinate) {
        return getSquare(coordinate).getPiece() != null;
    }

    public int getColumns() {
        return column;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Square[] getSquares() {
        return squares;
    }
    public void printMovements() {
        for (MovementHistory movement : movements) {
            System.out.println(movement.piece().getName() + " moved from " + movement.initialSquare().column() + "," + movement.initialSquare().row() + " to " + movement.finalSquare().column() + "," + movement.finalSquare().row());
        }
    }

    public List<MovementHistory> getMovements() {
        return movements;
    }

    public String displayBoard() {
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < row; i++) {
            board.append("---------------------------------\n");
            for (int j = 0; j < column; j++) {
                board.append("|");
                Coordinate tempCoordinate = new Coordinate(j, i);
                Square square = getSquare(tempCoordinate);
                if (square.getPiece() != null) {
                    Piece p = square.getPiece();
                    String pieceString = p.getColor() == SideColor.White ? "\u001B[32m" : "\u001B[31m";
                    pieceString += p.getPieceId();
                    pieceString += "\u001B[0m";
                    board.append(pieceString);
                } else {
                    board.append(" - ");
                }
            }
            board.append("|\n");
        }
        board.append("---------------------------------\n");
        return board.toString();
    }

}
