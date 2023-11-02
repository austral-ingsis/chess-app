package common;

import common.boardMovement.BoardMovement;
import common.boardValidator.Validator;
import common.turn.Turn;
import common.winningCondition.WinCondition;

import java.util.List;

public class GameMode {
    private Board casilleros;
    private List<BoardMovement> boardMovement;

    private List<Validator> validators;
    private Turn turn;
    private WinCondition winCondition;

    public GameMode(Board cas, List<BoardMovement> boardMovement, List<Validator> validators1, Turn turn, WinCondition winCondition){
        casilleros = cas;
        this.boardMovement = boardMovement;
        this.validators = validators1;
        this.turn = turn;
        this.winCondition = winCondition;
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

    public WinCondition getWinCondition() {
        return winCondition;
    }
}
