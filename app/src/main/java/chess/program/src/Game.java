package chess.program.src;

import chess.program.src.boardMovement.BoardMovement;
import chess.program.src.boardValidator.Validator;
import chess.program.src.enums.Color;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private GameMode gameMode;
    private Board board;
    private List<Player> players;


    public Game ( GameMode gameMode1, List<Player> players){
        gameMode = gameMode1;
        board = gameMode.getCasilleros();
        this.players = players;
    }


    private Game(GameMode gameMode, Board board, List<Player> players) {
        this.gameMode = gameMode;
        this.board = board;
        this.players = players;
    }

    public Game move(Position initial, Position finalPosition){
            if(board.getPiece(initial) == null){return new Game(this.gameMode,this.board,this.players);}
            Piece piece = board.getPiece(initial);
            List<Player> players1 = copyPlayers();
            if(piece.getColor() != gameMode.getTurn().isTurn(players1).getColor()){return new Game(this.gameMode,this.board,this.players);}


            else {
                BoardResult br = this.makeMove(initial,finalPosition);
                if (br.isChanged()){this.players = players1;}
            }

        return new Game(this.gameMode,this.board,this.players);
    }


    private BoardResult makeMove(Position initial, Position finalPosition){

        if(board.getPiece(initial) == null){return new BoardResult(board,false);}


        if (board.mover(initial,finalPosition)){

            List<Validator> validatorList  = gameMode.getValidators();
            for (Validator validator: validatorList) {
                if (!validator.validate(initial,finalPosition,this.board)){
                    System.out.println("Movimiento invalido");
                    return new BoardResult(this.board,false);
                }
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

    public Color getTurn(){
        return players.get(0).getColor();
    }

}
