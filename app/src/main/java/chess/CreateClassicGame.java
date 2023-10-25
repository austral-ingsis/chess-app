package chess;

import chess.Models.*;
import chess.Moves.DiagonalMove;
import chess.Moves.HorizontalMove;
import chess.Moves.JumpMove;
import chess.Moves.VerticalMove;

import java.util.ArrayList;
import java.util.List;

public class CreateClassicGame {
    public static Game ClassicGame() {
        Player player1 = new Player();
        Player player2 = new Player();
        List<Piece> blackPieces = new ArrayList<>();
        List<Piece> whitePieces = new ArrayList<>();
        PieceBuilder pieceBuilder = new PieceBuilder();

        List<Move> pawnMovements = new ArrayList<>();
        pawnMovements.add(new VerticalMove(1, false, false));
        pawnMovements.add(new VerticalMove(2, false, false));

        List<Move> pawnEatMoves = new ArrayList<>();
        pawnEatMoves.add(new DiagonalMove(1, -1, false));
        pawnEatMoves.add(new DiagonalMove(1, 1, false));

        List<Move> rookMovements = new ArrayList<>();
        rookMovements.add(new VerticalMove(false, true));
        rookMovements.add(new HorizontalMove(false));

        List<Move> bishopMovements = new ArrayList<>();
        bishopMovements.add(new DiagonalMove(false));

        List<Move> queenMovements = new ArrayList<>();
        queenMovements.add(new VerticalMove(false, true));
        queenMovements.add(new HorizontalMove(false));
        queenMovements.add(new DiagonalMove(false));

        List<Move> knightMovements = new ArrayList<>();
        knightMovements.add(new JumpMove(2, 1));
        knightMovements.add(new JumpMove(2, -1));
        knightMovements.add(new JumpMove(-2, 1));
        knightMovements.add(new JumpMove(-2, -1));
        knightMovements.add(new JumpMove(1, 2));
        knightMovements.add(new JumpMove(1, -2));
        knightMovements.add(new JumpMove(-1, 2));
        knightMovements.add(new JumpMove(-1, -2));

        List<Move> kingMovements = new ArrayList<>();
        kingMovements.add(new VerticalMove(1, false, true));
        kingMovements.add(new HorizontalMove(1, false));
        kingMovements.add(new DiagonalMove(1, 1, false));

        whitePieces.add(pieceBuilder.createPiece("PawnW1", "Pawn", new Coordinate(0, 1), pawnMovements, pawnEatMoves, false, SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("Pawn", "PawnW2", new Coordinate(1, 1), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("Pawn", "PawnW3", new Coordinate(2, 1), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("Pawn", "PawnW4", new Coordinate(3, 1), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("Pawn", "PawnW5", new Coordinate(4, 1), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("Pawn", "PawnW6", new Coordinate(5, 1), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("Pawn", "PawnW7", new Coordinate(6, 1), SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("Pawn", "PawnW8", new Coordinate(7, 1), SideColor.White));

        whitePieces.add(pieceBuilder.createPiece("RookW1", "Rook", new Coordinate(0, 0), rookMovements, false, SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("Rook", "RookW2", new Coordinate(7, 0), SideColor.White));

        whitePieces.add(pieceBuilder.createPiece("BishopW1", "Bishop", new Coordinate(2, 0), bishopMovements, false, SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("Bishop", "BishopW2", new Coordinate(5, 0), SideColor.White));

        whitePieces.add(pieceBuilder.createPiece("KnightW1", "Knight", new Coordinate(1, 0), knightMovements, false, SideColor.White));
        whitePieces.add(pieceBuilder.clonePiece("Knight", "KnightW2", new Coordinate(6, 0), SideColor.White));

        whitePieces.add(pieceBuilder.createPiece("QueenW1", "Queen", new Coordinate(3, 0), queenMovements, false, SideColor.White));
        whitePieces.add(pieceBuilder.createPiece("KingW", "King", new Coordinate(4, 0), kingMovements, true, SideColor.White));


        blackPieces.add(pieceBuilder.clonePiece("Pawn", "PawnB1", new Coordinate(0, 6), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("Pawn", "PawnB2", new Coordinate(1, 6), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("Pawn", "PawnB3", new Coordinate(2, 6), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("Pawn", "PawnB4", new Coordinate(3, 6), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("Pawn", "PawnB5", new Coordinate(4, 6), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("Pawn", "PawnB6", new Coordinate(5, 6), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("Pawn", "PawnB7", new Coordinate(6, 6), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("Pawn", "PawnB8", new Coordinate(7, 6), SideColor.Black));

        blackPieces.add(pieceBuilder.clonePiece("Rook", "RookB1", new Coordinate(0, 7), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("Rook", "RookB2", new Coordinate(7, 7), SideColor.Black));

        blackPieces.add(pieceBuilder.clonePiece("Bishop", "BishopB1", new Coordinate(2, 7), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("Bishop", "BishopB2", new Coordinate(5, 7), SideColor.Black));

        blackPieces.add(pieceBuilder.clonePiece("Knight", "KnightB1", new Coordinate(1, 7), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("Knight", "KnightB2", new Coordinate(6, 7), SideColor.Black));

        blackPieces.add(pieceBuilder.clonePiece("Queen", "QueenB1", new Coordinate(3, 7), SideColor.Black));
        blackPieces.add(pieceBuilder.clonePiece("King", "KingB", new Coordinate(4, 7), SideColor.Black));


        Board board = new Board(8, 8, blackPieces, whitePieces);
        return new Game(player1, player2, board);
    }
}
