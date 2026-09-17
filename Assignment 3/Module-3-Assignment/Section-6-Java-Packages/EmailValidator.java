// Q25: Regular Expressions
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EmailValidator {
    public static void main(String[] args) {
        // 1. Take an email address as input from the user.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an email address to validate: ");
        
        if (scanner.hasNextLine()) {
            String inputEmail = scanner.nextLine();
            
            // 2. Create a suitable regular expression for basic email validation.
            // Explanation: 
            // ^ starts the string.
            // [A-Za-z0-9+_.-]+ matches one or more alphanumeric characters and certain symbols.
            // @ matches the "@" symbol literally.
            // [A-Za-z0-9.-]+ matches the domain name.
            // $ ends the string.
            String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

            // 3. Compile the regex using Pattern.
            // Pattern represents a compiled representation of a regular expression.
            Pattern pattern = Pattern.compile(regex);

            // 4. Use Matcher to check the input.
            // Matcher is an engine that performs match operations on a character sequence against a Pattern.
            Matcher matcher = pattern.matcher(inputEmail);

            // 5. Display whether the email is valid or invalid.
            System.out.println("\n--- Validation Result ---");
            System.out.println("Input Email: " + inputEmail);
            
            if (matcher.matches()) {
                System.out.println("Result: VALID Email Address.");
            } else {
                System.out.println("Result: INVALID Email Address.");
            }
        } else {
            System.out.println("No input provided.");
        }
        
        scanner.close();
    }
}
