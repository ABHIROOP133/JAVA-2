package Question10_ThrowThrows;

/**
 * 1. Create a Java program to calculate the factorial of a number.
 */
public class FactorialCalculator {

    public static void main(String[] args) {
        System.out.println("--- Factorial Calculation Tests ---");
        
        // Test with a valid positive number: 5
        testFactorial(5);
        
        // Test with zero: 0
        testFactorial(0);
        
        // Test with a negative number: -3
        testFactorial(-3);
    }
    
    /**
     * Helper method to test the calculation and handle exceptions.
     * 6. Handle the exception properly using try-catch in the calling/main method.
     */
    private static void testFactorial(int number) {
        System.out.print("Calculating factorial of " + number + " -> ");
        try {
            // Calling the method that declares 'throws'
            long result = calculateFactorial(number);
            
            // 7. For a valid non-negative number, calculate and display its factorial.
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }
    }

    /**
     * 2. Create a method for calculating factorial.
     * 3. Demonstrate the use of the `throws` keyword in the method declaration.
     * 
     * The 'throws' keyword is used in the method signature to declare that 
     * this method might throw an IllegalArgumentException, warning the caller
     * that they need to handle it.
     */
    public static long calculateFactorial(int n) throws IllegalArgumentException {
        
        // 5. If the input number is negative, explicitly throw an appropriate exception.
        if (n < 0) {
            // 4. Demonstrate the use of the `throw` keyword inside the program/method.
            // The 'throw' keyword is used here to physically create and throw 
            // the exception object when the invalid condition is met.
            throw new IllegalArgumentException("Number cannot be negative.");
        }
        
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        
        return factorial;
    }
}
