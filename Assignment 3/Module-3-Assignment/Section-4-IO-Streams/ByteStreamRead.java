// Q13: Reading File Using Byte Stream (FileInputStream)
import java.io.FileInputStream;
import java.io.IOException;

public class ByteStreamRead {
    public static void main(String[] args) {
        String filePath = "input.txt"; 
        System.out.println("Reading from " + filePath + " using FileInputStream:\n");
        
        // try-with-resources ensures the stream is automatically closed
        try (FileInputStream fis = new FileInputStream(filePath)) {
            int data;
            // Read byte by byte until the end of the file (-1)
            while ((data = fis.read()) != -1) {
                // Cast the byte to a char to print it correctly
                System.out.print((char) data);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
