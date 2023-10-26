package common.turn;

import common.Player;
import common.Position;

import java.util.List;

public interface Turn {

    public Player isTurn(List<Player> players, Position initial, Position finalPosition);

}
