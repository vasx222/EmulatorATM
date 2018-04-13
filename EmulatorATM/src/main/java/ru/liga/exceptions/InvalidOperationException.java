package ru.liga.exceptions;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException() {
        super("Impossible to perform operation!");
    }
}
