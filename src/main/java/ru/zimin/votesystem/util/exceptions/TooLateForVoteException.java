package ru.zimin.votesystem.util.exceptions;

public class TooLateForVoteException extends RuntimeException {
    public TooLateForVoteException(String message) {
        super(message);
    }
}
