package common.results;

public record CheckResult<T, R>(T successfulResult, R outputResult, String message) {
}
