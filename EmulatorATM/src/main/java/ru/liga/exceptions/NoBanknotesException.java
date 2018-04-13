package ru.liga.exceptions;

public class NoBanknotesException extends RuntimeException {
    public NoBanknotesException() {
        super("Not enough banknotes to issue the specified amount!");
    }
}
