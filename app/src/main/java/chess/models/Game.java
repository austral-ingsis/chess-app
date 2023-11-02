package chess.models;

import common.logic.CheckLegalMove;
import common.logic.WinCondition;
import common.results.MoveResults;


import java.util.Objects;
import java.util.Random;
import java.util.Stack;

public class Game {
    private final Player player1;
    private final Player player2;
    private final Stack<Board> boardStack = new Stack<>();
    private TurnHandler turnHandler;
    private final WinCondition winCondition;
    private final CheckLegalMove checkLegalMove;


    public Game(Player player1, Player player2, Board board,SideColor startingPlayer, WinCondition winCondition, CheckLegalMove checkLegalMove) {
        this.player1 = player1;
        this.player2 = player2;
        this.boardStack.push(board);
        this.turnHandler = new TurnHandler(startingPlayer);
        this.winCondition = winCondition;
        this.checkLegalMove = checkLegalMove;
        setGame();
    }

    public void setGame() {
        System.out.println("Game Started");
        Random rand = new Random();
        int randomNum = rand.nextInt(2);
        if (randomNum == 0) {
            player1.setColor(SideColor.White);
            player2.setColor(SideColor.Black);
        } else {
            player1.setColor(SideColor.Black);
            player2.setColor(SideColor.White);
        }
    }

    public MoveResults<Board,Boolean> movePiece(Coordinate initial, Coordinate toSquare, Player currentPlayer) {
        Piece piece = boardStack.peek().getSquare(initial).getPiece();
        if (Objects.equals(piece.getName(), "null")) {
            return new MoveResults<>(boardStack.peek(), true, "Piece not found");
        }
        if(piece.getColor().equals(currentPlayer.getColor())) {
            MoveResults<Board, Boolean> res = piece.movePiece(initial,toSquare, boardStack.peek(),winCondition,checkLegalMove);
            if (res.errorResult()) {
                return new MoveResults<>(boardStack.peek(), true, res.message());
            }
            turnHandler = turnHandler.nextTurn();
            boardStack.push(res.successfulResult());
            return new MoveResults<>(boardStack.peek(), false, res.message());
        }
        else{
            return new MoveResults<>(boardStack.peek(), true, "Piece not same color as player");
        }
    }


    public Board getBoard() {
        return boardStack.peek();
    }

    public TurnHandler getTurnHandler() {
        return turnHandler;
    }

    public Player getCurrentPlayer() {
        if (turnHandler.turn() == player1.getColor()) {
            return player1;
        }
        return player2;
    }
}
