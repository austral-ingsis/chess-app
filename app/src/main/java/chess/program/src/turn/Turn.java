package chess.program.src.turn;

import chess.program.src.Player;

import java.util.List;

public interface Turn {

    public Player isTurn(List<Player> players);

}
