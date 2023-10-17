package chess.program.src;

import chess.program.src.boardMovement.BoardMovement;
import chess.program.src.boardValidator.Validator;

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

            List<Player> players1 = copyPlayers();
            Piece piece = board.getPiece(initial);
            if(piece.getColor() == gameMode.getTurn().isTurn(players1).getColor()){
                this.makeMove(initial,finalPosition);
                this.players = players1;
            }

        return new Game(this.gameMode,this.board,this.players);
    }


    private Board makeMove(Position initial, Position finalPosition){

        if(board.getPiece(initial) == null){return board;}


        if (board.mover(initial,finalPosition)){

            List<Validator> validatorList  = gameMode.getValidators();
            for (Validator validator: validatorList) {
                if (!validator.validate(initial,finalPosition,this.board)){
                    System.out.println("Movimiento invalido");
                    return this.board;
                }
            }
            List<BoardMovement> boardMovementList = gameMode.getBoardMovement();
            for (BoardMovement boardMovement: boardMovementList) {
               BoardResult br = boardMovement.move(board,initial,finalPosition);
               if(br.isChanged()){
                   System.out.println("Movimiento valido");
                   this.board = br.getBoardResult();
                   return board;
               }
            }

            board.put(finalPosition, board.getPiece(initial));
            board.put(initial, null);
            System.out.println("Movimiento valido");
            return board;

    }
    return board;
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

}
