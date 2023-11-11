package chess.logic.classicGame;

import common.models.*;
import common.moves.Move;
import common.moves.DiagonalMove;
import chess.logic.moves.HorizontalMove;
import chess.logic.moves.JumpMove;
import chess.logic.moves.VerticalMove;

import java.util.ArrayList;
import java.util.List;

    public class ClassicGame {
        public static Game CreateGame() {
            Player player1 = new Player();
            Player player2 = new Player();
            List<Piece> blackPieces = new ArrayList<>();
            List<Piece> whitePieces = new ArrayList<>();
            PieceBuilder pieceBuilder = new PieceBuilder();

            List<Move> pawnMovements = new ArrayList<>();
            pawnMovements.add(new VerticalMove(1, false));
            pawnMovements.add(new VerticalMove(2, false));

            List<Move> pawnEatMoves = new ArrayList<>();
            pawnEatMoves.add(new DiagonalMove(1, -1));
            pawnEatMoves.add(new DiagonalMove(1, 1));

            List<Move> rookMovements = new ArrayList<>();
            rookMovements.add(new VerticalMove( true));
            rookMovements.add(new HorizontalMove(false));

            List<Move> bishopMovements = new ArrayList<>();
            bishopMovements.add(new DiagonalMove());

            List<Move> queenMovements = new ArrayList<>();
            queenMovements.add(new VerticalMove( true));
            queenMovements.add(new HorizontalMove(false));
            queenMovements.add(new DiagonalMove());

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
            kingMovements.add(new VerticalMove(1, true));
            kingMovements.add(new HorizontalMove(1, false));
            kingMovements.add(new DiagonalMove(1, 1));
            whitePieces.add(pieceBuilder.createPiece("pawn", new Coordinate(1, 2), pawnMovements, pawnEatMoves, false, SideColor.White));
            whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(2, 2), SideColor.White));
            whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(3, 2), SideColor.White));
            whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(4, 2), SideColor.White));
            whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(5, 2), SideColor.White));
            whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(6, 2), SideColor.White));
            whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(7, 2), SideColor.White));
            whitePieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(8, 2), SideColor.White));

            whitePieces.add(pieceBuilder.createPiece("rook", new Coordinate(1, 1), rookMovements, false, SideColor.White));
            whitePieces.add(pieceBuilder.clonePiece("rook", new Coordinate(8, 1), SideColor.White));

            whitePieces.add(pieceBuilder.createPiece("bishop", new Coordinate(3, 1), bishopMovements, false, SideColor.White));
            whitePieces.add(pieceBuilder.clonePiece("bishop", new Coordinate(6, 1), SideColor.White));

            whitePieces.add(pieceBuilder.createPiece("knight", new Coordinate(2, 1), knightMovements, false, SideColor.White));
            whitePieces.add(pieceBuilder.clonePiece("knight", new Coordinate(7, 1), SideColor.White));

            whitePieces.add(pieceBuilder.createPiece("queen", new Coordinate(4, 1), queenMovements, false, SideColor.White));
            whitePieces.add(pieceBuilder.createPiece("king", new Coordinate(5, 1), kingMovements, true, SideColor.White));

            blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(1, 7), SideColor.Black));
            blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(2, 7), SideColor.Black));
            blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(3, 7), SideColor.Black));
            blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(4, 7), SideColor.Black));
            blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(5, 7), SideColor.Black));
            blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(6, 7), SideColor.Black));
            blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(7, 7), SideColor.Black));
            blackPieces.add(pieceBuilder.clonePiece("pawn", new Coordinate(8, 7), SideColor.Black));

            blackPieces.add(pieceBuilder.clonePiece("rook", new Coordinate(1, 8), SideColor.Black));
            blackPieces.add(pieceBuilder.clonePiece("rook", new Coordinate(8, 8), SideColor.Black));

            blackPieces.add(pieceBuilder.clonePiece("bishop", new Coordinate(3, 8), SideColor.Black));
            blackPieces.add(pieceBuilder.clonePiece("bishop", new Coordinate(6, 8), SideColor.Black));

            blackPieces.add(pieceBuilder.clonePiece("knight", new Coordinate(2, 8), SideColor.Black));
            blackPieces.add(pieceBuilder.clonePiece("knight", new Coordinate(7, 8), SideColor.Black));

            blackPieces.add(pieceBuilder.clonePiece("queen", new Coordinate(4, 8), SideColor.Black));
            blackPieces.add(pieceBuilder.clonePiece("king", new Coordinate(5, 8), SideColor.Black));



            Board board = new Board(8, 8, blackPieces, whitePieces);
            return new Game(player1, player2, board,SideColor.White, new ClassicWinCondition());
        }
    }

