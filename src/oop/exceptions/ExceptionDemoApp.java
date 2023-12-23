package oop.exceptions;

// There are 3 types of exceptions:
// 1. Checked exception
// Before we compile our program, Java checks our code to ensure
// that we have handled all possible exceptions that might occur.
// If we haven't, our code will be marked as a red line in our IDE, and it won't compile until we address it.
// To handle it:
// Either we add a try-catch block or declare the exception with the throws keyword in the method signature.
// 2. Runtime exception (Unchecked exception)
// The code could be compiled even if we have not handled the exception possibilities.
// 3. Error
// A fatal error that is not recommended to be caught.
// Example: Connection failure to the database; in that case, we want to stop our app.
public class ExceptionDemoApp {
    public static void main(String[] args) {
        // Checked exception
        // must handle the exception possibilities
        String invalidEmail = "this is not a valid email";
        try {
            ValidationUtil.validateEmailFormat(invalidEmail);
        } catch (EmailFormatValidationException | NullPointerException e) {
            // When encounter EmailFormatValidationException or NullPointerException
            // It will come to this block
            System.out.println(e.getMessage());
            // add print stack trace to more informative logs
            // showing which problematic line and the root cause
            e.printStackTrace();
        } finally {
            // finally will be executed regardless of whether the execution
            // was successful or encountered a failure.
            System.out.println("Finally");
        }

        // Unchecked exception -- Runtime exception
        // The following code will throw a null pointer exception.
        // Adding try-catch to the code below is optional,
        // but a runtime exception will break the program execution.
        String name = null;
        // ValidationUtil.validateString(name);
        // System.out.println(name.length());

        try {
            ValidationUtil.validateString(name);
            System.out.println(name.length());
        } catch (EmptyStringException e) {
            System.out.println(e.getMessage());
        }

        // error
        // It is not recommended to handle the exception
        boolean isSuccessToConnectToDatabase = false;
        ValidationUtil.validateDatabaseConnection(isSuccessToConnectToDatabase);
    }
}
