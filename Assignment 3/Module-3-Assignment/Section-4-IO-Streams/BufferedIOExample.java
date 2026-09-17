// Q17: Buffered I/O (BufferedReader and BufferedWriter)
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedIOExample {
    public static void main(String[] args) {
        String sourceFile = "example.txt"; // File created in Q16
        String targetFile = "buffered_output.txt";
        
        System.out.println("--- Buffered I/O Demonstration ---");

        // 1. Reading a file using BufferedReader
        // 2. Writing to a file using BufferedWriter
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
             
            String line;
            System.out.println("Reading from " + sourceFile + " and writing to " + targetFile + "...");
            
            // BufferedReader allows reading line-by-line using readLine()
            while ((line = reader.readLine()) != null) {
                // Write the line to the target file
                writer.write(line);
                writer.newLine(); // Add a newline character
                
                // Also print it to console to demonstrate reading
                System.out.println("Read Line: " + line);
            }
            
            System.out.println("Buffered read and write completed successfully.");

        } catch (IOException e) {
            System.out.println("An I/O Error occurred: " + e.getMessage());
        }
        
        System.out.println("\n--- Why Buffering Improves Performance ---");
        System.out.println("Without buffering, every single read() or write() operation directly accesses");
        System.out.println("the physical disk (or OS), which is very slow due to system overhead.");
        System.out.println("Buffering uses an internal memory array (buffer). It reads/writes a large");
        System.out.println("chunk of data at once to/from the disk. The program then accesses the");
        System.out.println("fast memory buffer for individual characters or lines, significantly reducing disk I/O.");
    }
}
