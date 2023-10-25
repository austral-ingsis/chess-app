package chess.Results;

public record PlayerMove<String, Integer>(String pieceName, Integer toSquareX, Integer toSquareY) {
}
