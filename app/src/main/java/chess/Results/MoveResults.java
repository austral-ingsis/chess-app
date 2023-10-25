package chess.Results;

public record MoveResults<T, R>(T successfulResult, R errorResult, String message) {
}
