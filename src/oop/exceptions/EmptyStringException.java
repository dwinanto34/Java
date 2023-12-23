package oop.exceptions;

// Unchecked exception
// must extends RuntimeException
public class EmptyStringException extends RuntimeException{
    public EmptyStringException(String message) {
        super(message);
    }
}
