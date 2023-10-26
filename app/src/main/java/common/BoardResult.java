package common;

public class BoardResult {

    private Board boardResult;
    private boolean changed;

    public BoardResult(Board boardResult, boolean changed) {
        this.boardResult = boardResult;
        this.changed = changed;
    }

    public Board getBoardResult() {
        return boardResult;
    }

    public boolean isChanged() {
        return changed;
    }
}
