package oop.exceptions;

// Error
// Must extends Error
public class DatabaseConnectionException extends Error {
    public DatabaseConnectionException(String message) {
        super(message);
    }
}
