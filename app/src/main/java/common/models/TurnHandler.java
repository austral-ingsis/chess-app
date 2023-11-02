package common.models;

public record TurnHandler(SideColor turn) {

    public TurnHandler nextTurn() {
        if (turn == SideColor.White) {
            return new TurnHandler(SideColor.Black);
        }
        return new TurnHandler(SideColor.White);
    }
}
