package ru.homer.leaderboard.exception;

/**
 * Created by vadmurzakov on 22.07.17.
 */
public class IssueTypeNotFound extends RuntimeException {
    public IssueTypeNotFound(String message) {
        super(message);
    }
}
