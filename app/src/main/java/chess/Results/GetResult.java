package chess.Results;

import java.util.Optional;

public record GetResult<T, R>(Optional<T> successfulResult, R errorResult) {
}

