// Q23: Using java.lang Package
// The java.lang package provides classes that are fundamental to the design of the 
// Java programming language. It is automatically imported into every Java program.

public class JavaLangDemo {
    public static void main(String[] args) {
        System.out.println("--- Demonstrating java.lang.Math methods ---");

        // 1. Math.random()
        // Returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0.
        double randomNum = Math.random();
        System.out.println("1. Math.random() result:");
        System.out.println("   Random number between 0.0 and 1.0: " + randomNum);
        
        // 2. Math.abs()
        // Returns the absolute (positive) value of a given number.
        int negativeNumber = -50;
        int absValue = Math.abs(negativeNumber);
        System.out.println("\n2. Math.abs() result:");
        System.out.println("   Absolute value of " + negativeNumber + " is: " + absValue);

        // 3. Math.pow()
        // Returns the value of the first argument raised to the power of the second argument.
        double base = 2.0;
        double exponent = 3.0;
        double powerResult = Math.pow(base, exponent);
        System.out.println("\n3. Math.pow() result:");
        System.out.println("   " + base + " raised to the power of " + exponent + " is: " + powerResult);
    }
}
