package chess.program.src.conector;

import chess.program.src.Board;
import chess.program.src.Piece;
import chess.program.src.enums.Type;
import edu.austral.dissis.chess.gui.*;
import chess.program.src.enums.Color;

import java.util.ArrayList;
import java.util.List;

public class Conector {
    public static BoardSize adaptBoard(Board board){
        return new BoardSize(board.getRow(), board.getColumn());
    }
    public static Position adaptPosition(chess.program.src.Position position){
        return new Position(position.getRow()  , position.getColumn()  );
    }
    public static PlayerColor adaptColour(Color colour){
        if (colour == Color.BLACK) return PlayerColor.BLACK;
        else return PlayerColor.WHITE;
    }
    public static ChessPiece adaptPieces(Piece piece, chess.program.src.Position position){

       ChessPiece piece1 = new ChessPiece(piece.getId(), adaptColour(piece.getColor()), adaptPosition(position), adaptName(piece.getType()));
       return piece1;

    }

    private static String adaptName(Type name){

        return switch (name) {
            case PAWN -> "pawn";
            case TOWER -> "rook";
            case KNIGHT -> "knight";
            case BISHOP -> "bishop";
            case QUEEN -> "queen";
            case KING -> "king";
            case FRSTKING -> "king";
            case FIRSTPAWN -> "pawn";
            case FIRSTTOWER -> "rook";
            default -> "empty";
        };
    }
   /* public static Movement toMovement(Move move, Board board){

        Chess.Position fromPosition = board.getPosition(move.getFrom().getRow() -1 , move.getFrom().getColumn() -1 );
        Chess.Position toPosition = board.getPosition(move.getTo().getRow() -1 , move.getTo().getColumn() -1 );

        return new Movement(fromPosition, toPosition);

    }

    */

    public static List<ChessPiece> getPieces(Board board) {
        List <ChessPiece> pieces = new ArrayList<>();
        for (int x =1; x<=board.getRow(); x++){
            for (int y =1; y<= board.getColumn(); y++){
                chess.program.src.Position position = new chess.program.src.Position(x ,y);
                if (board.getPiece(position) != null){
                    pieces.add(adaptPieces(board.getPiece(position),position));
                }
            }
        }
        return pieces;
    }
    public static chess.program.src.Position getPos(Position position){
        return new chess.program.src.Position(position.getRow(), position.getColumn());
    }
}
