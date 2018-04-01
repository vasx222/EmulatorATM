package ru.liga.exceptions;

public class InvalidOperationException extends Exception {
    public InvalidOperationException() {
        super("Impossible to perform operation!");
    }
}
