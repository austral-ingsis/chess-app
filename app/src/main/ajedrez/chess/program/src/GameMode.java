package chess.program.src;

import chess.program.src.boardMovement.BoardMovement;
import chess.program.src.boardValidator.Validator;
import chess.program.src.turn.Turn;

import java.util.List;
import java.util.Map;

public class GameMode {
    private Board casilleros;
    private List<BoardMovement> boardMovement;

    private List<Validator> validators;
    private Turn turn;

    public GameMode(Board cas, List<BoardMovement> boardMovement, List<Validator> validators1, Turn turn){
        casilleros = cas;
        this.boardMovement = boardMovement;
        this.validators = validators1;
        this.turn = turn;
    }


    public Board getCasilleros() {
        return casilleros;
    }

    public List<BoardMovement> getBoardMovement() {
        return boardMovement;
    }

    public List<Validator> getValidators() {
        return validators;
    }

    public Turn getTurn(){
        return turn;
    }
}
