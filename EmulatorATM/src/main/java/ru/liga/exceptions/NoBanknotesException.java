package ru.liga.exceptions;

public class NoBanknotesException extends Exception {
    public NoBanknotesException() {
        super("Not enough banknotes to issue the specified amount!");
    }
}
