package chess.Logic;

import chess.Models.SideColor;

public class TurnHandler {
    private final SideColor turn;

    public TurnHandler(SideColor turn) {
        this.turn = turn;
    }

    public SideColor getTurn() {
        return turn;
    }

    public TurnHandler nextTurn() {
        if (turn == SideColor.White) {
            return new TurnHandler(SideColor.Black);
        }
        return new TurnHandler(SideColor.White);
    }
}
