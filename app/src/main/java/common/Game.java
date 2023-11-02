package common;

import chess.program.src.boardMovement.BoardMovement;
import chess.program.src.boardValidator.Validator;
import common.enums.Color;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private GameMode gameMode;
    private Board board;
    private List<Player> players;
    private boolean isFinished = false;


    public Game ( GameMode gameMode1, List<Player> players){
        gameMode = gameMode1;
        board = new Board(gameMode.getCasilleros());
        this.players = players;
        this.isFinished = false;
    }

    private Game(GameMode gameMode, Board board, List<Player> players,boolean isFinished) {
        this.gameMode = gameMode;
        this.board = board;
        this.players = players;
        this.isFinished = isFinished;
    }

    public Game move(Position initial, Position finalPosition) {
            boolean finish = false;
            if(board.getPiece(initial) == null){return new Game(this.gameMode,this.board,this.players,finish);}

            Piece piece = board.getPiece(initial);
            List<Player> players1 = copyPlayers();
            if(isTurn(piece,initial, finalPosition,players1)){
                return new Game(this.gameMode,this.board,this.players,finish);
            }


            else {
                BoardResult br = this.makeMove(initial,finalPosition);
                if (br.isChanged()){
                    this.players = players1;
                    if (gameMode.getWinCondition().winCondition(br.getBoardResult(),initial,finalPosition)){finish = true;}
            }}

        return new Game(this.gameMode,this.board,this.players,finish);

    }

    private boolean isTurn(Piece piece, Position initial, Position finalPosition, List<Player> players) {
        return piece.getColor() != gameMode.getTurn().isTurn(players, initial, finalPosition, board).getColor();
    }


    private BoardResult makeMove(Position initial, Position finalPosition){

        if(board.getPiece(initial) == null){return new BoardResult(board,false);}


        if (board.mover(initial,finalPosition)){

            List<Validator> validatorList  = gameMode.getValidators();
            if(!checkBoardValidators(validatorList,initial,finalPosition)){
                return new BoardResult(this.board,false);
            }


            List<BoardMovement> boardMovementList = gameMode.getBoardMovement();
            for (BoardMovement boardMovement: boardMovementList) {
               BoardResult br = boardMovement.move(board,initial,finalPosition);
               if(br.isChanged()){
                   System.out.println("Movimiento valido");
                   this.board = br.getBoardResult();
                   return br;
               }
            }

            board.put(finalPosition, board.getPiece(initial));
            board.put(initial, null);
            System.out.println("Movimiento valido");
            return new BoardResult(this.board,true);

    }
    return new BoardResult(board,false);
    }




    private boolean checkBoardValidators(List<Validator> validatorList,Position initial, Position finalPosition){
        for (Validator validator: validatorList) {
            if (!validator.validate(initial,finalPosition,this.board)){
                return false;
            }
        }
        return true;
    }




    public Board getBoard() {
        return board;
    }

    public List<Player> copyPlayers() {
        List<Player> copiedList = new ArrayList<>();
        for (Player player : players) {
            Player copiedPlayer = new Player(player.getName(), player.getColor());
            copiedList.add(copiedPlayer);
        }
        return copiedList;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isTurn(Color color){
        return players.get(0).getColor() == color;
    }

    public Color getTurn(){
        return players.get(0).getColor();
    }

    public boolean getIsFinished(){
        return isFinished;
    }


}
