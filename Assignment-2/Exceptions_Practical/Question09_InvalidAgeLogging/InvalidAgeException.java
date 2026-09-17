package Question09_InvalidAgeLogging;

/**
 * 1. Create a custom checked exception class named InvalidAgeException.
 * 2. The exception should extend Exception.
 */
public class InvalidAgeException extends Exception {
    
    // Constructor that accepts a custom error message
    public InvalidAgeException(String message) {
        super(message);
    }
}
