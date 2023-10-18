package chess.program.src.turn;

import chess.program.src.Player;

import java.util.List;

public class NormalTurn implements Turn{
    @Override
    public Player isTurn(List<Player> players) {
        Player player = players.get(0);
        players.remove(0);
        players.add(player);
        return player;
    }
}
