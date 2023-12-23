package oop.exceptions;

// Checked exception
// must extends Throwable
public class EmailFormatValidationException extends Throwable {
    public EmailFormatValidationException(String message) {
        super(message);
    }
}
