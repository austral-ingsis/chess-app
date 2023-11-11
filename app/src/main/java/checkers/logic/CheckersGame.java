package checkers.logic;

import common.models.*;
import common.moves.DiagonalMove;
import common.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class CheckersGame {
    public static Game CreateGame() {
        Player player1 = new Player();
        Player player2 = new Player();
        List<Piece> blackPieces = new ArrayList<>();
        List<Piece> whitePieces = new ArrayList<>();
        PieceBuilder pieceBuilder = new PieceBuilder();

        List<Move> pieceMovements = new ArrayList<>();
        pieceMovements.add(new DiagonalMove(1, 1));
        pieceMovements.add(new DiagonalMove(1, -1));

        List<Move> pieceEatMoves = new ArrayList<>();
        pieceEatMoves.add(new CheckersEatMove(1, 1));
        pieceEatMoves.add(new CheckersEatMove(1, -1));

        whitePieces.add(pieceBuilder.createPiece("pawn", new Coordinate(1, 1), pieceMovements, pieceEatMoves, false, SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(3, 1), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(5, 1), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(7, 1), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(2, 2), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(4, 2), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(6, 2), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(8, 2), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(1, 3), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(3, 3), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(5, 3), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(7, 3), SideColor.White));

        blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(2, 6), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(4, 6), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(6, 6), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(8, 6), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(1, 7), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(3, 7), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(5, 7), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(7, 7), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(2, 8), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(4, 8), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(6, 8), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(8, 8), SideColor.Black));

        Board board= new Board(8, 8,blackPieces,whitePieces);
        return new Game(player1, player2,board, SideColor.Black, new CheckersWinCondition());
    }

}
