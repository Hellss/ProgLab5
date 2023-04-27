package main.java.Exceptions;

public class EmptyCollectionException extends RuntimeException {

    public EmptyCollectionException(String message) {
        super(message);
    }
}