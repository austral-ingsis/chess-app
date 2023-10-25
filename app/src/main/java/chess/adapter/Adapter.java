package chess.adapter;

import chess.Models.Board;

import java.util.ArrayList;
import java.util.List;

import chess.Models.Coordinate;
import chess.Models.Piece;
import chess.Models.SideColor;
import edu.austral.dissis.chess.gui.*;

public class Adapter {
    public static List<ChessPiece> getCurrentPieces(Board board) {
        List<ChessPiece> pieces = new ArrayList<>();
        for (Piece piece : board.getPieces()) {
            pieces.add(new ChessPiece(piece.getName(),convertPlayerColor(piece.getColor()), convertCoordinateToPosition(piece.getInitialSquare()), piece.getPieceId()));
        }
        return pieces;
    }

    public static Position convertCoordinateToPosition(Coordinate initialSquare) {
        return new Position(initialSquare.column(), initialSquare.row());
    }

    public static Coordinate convertPositionToCoordinate(Position position) {
        return new Coordinate(position.component1(), position.component2());
    }

    public static PlayerColor convertPlayerColor(SideColor color) {
        if (color == SideColor.Black) {
            return PlayerColor.BLACK;
        } else {
            return PlayerColor.WHITE;
        }
    }

    public static BoardSize getBoardSize(Board board) {
        return new BoardSize(board.getColumns(), board.getRows());
    }


}
