package oop.exceptions;

public class ValidationUtil {
    // checked exception
    public static void validateEmailFormat(String email) throws EmailFormatValidationException {
        if (email.contains("@") == false) {
            throw new EmailFormatValidationException("The email format is not valid");
        }
    }

    // unchecked exception (Runtime exception)
    // the declaration of throws EmptyStringException is optional
    public static void validateString(String email) throws EmptyStringException {
        if (email == null || email.length() == 0) {
            throw new EmptyStringException("String can't be empty");
        }
    }

    // error
    public static void validateDatabaseConnection(boolean isSuccessToConnectToDatabase) throws DatabaseConnectionException {
        if (isSuccessToConnectToDatabase == false) {
            throw new DatabaseConnectionException("Fail to connect to database");
        }
    }
}
