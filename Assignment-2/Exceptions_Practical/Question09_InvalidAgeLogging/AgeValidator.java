package Question09_InvalidAgeLogging;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

/**
 * 3. Create a Java program that accepts or defines a user's age.
 */
public class AgeValidator {
    
    // Create a Logger for this class
    private static final Logger logger = Logger.getLogger(AgeValidator.class.getName());

    public static void main(String[] args) {
        // Setup file logging before executing logic
        setupLogger();
        
        System.out.println("--- Testing with age: 17 ---");
        checkUserAge(17);
        
        System.out.println("\n--- Testing with age: 20 ---");
        checkUserAge(20);
    }
    
    /**
     * Configures the logger to write output to a file instead of the console.
     */
    private static void setupLogger() {
        try {
            // 8. Use a proper FileHandler so the log is actually written to a .log file.
            // true parameter means we append to the existing log file instead of overwriting
            FileHandler fileHandler = new FileHandler("invalid_age.log", true);
            
            // Format log messages as simple text instead of XML
            fileHandler.setFormatter(new SimpleFormatter());
            
            // Attach the file handler to our logger
            logger.addHandler(fileHandler);
            
            // Prevent the logger from also logging to the console (default behavior)
            logger.setUseParentHandlers(false);
            
        } catch (IOException e) {
            System.err.println("Failed to setup logger: " + e.getMessage());
        }
    }

    /**
     * Checks if the user is 18 or older.
     * @param age The age to validate
     */
    private static void checkUserAge(int age) {
        try {
            // 4. If age < 18, throw InvalidAgeException.
            if (age < 18) {
                // 7. The log should clearly indicate that the user is under 18 and include the relevant age.
                throw new InvalidAgeException("User is under 18. Age provided: " + age);
            }
            System.out.println("Age validation successful. User age is: " + age);
            
        } catch (InvalidAgeException e) {
            // 5. Handle the exception using try-catch.
            System.out.println("Caught exception: " + e.getMessage());
            
            // 6. Use java.util.logging to log the error to a file.
            logger.severe("Validation Error: " + e.getMessage());
        }
    }
}
