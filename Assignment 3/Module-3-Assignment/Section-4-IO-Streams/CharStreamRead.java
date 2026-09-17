// Q15: Reading a File Using Character Stream (FileReader)
import java.io.FileReader;
import java.io.IOException;

public class CharStreamRead {
    public static void main(String[] args) {
        // We will read the file created in Q14 (output.txt)
        String filePath = "output.txt"; 
        System.out.println("Reading from " + filePath + " using FileReader:\n");
        
        // try-with-resources to manage the reader
        try (FileReader fr = new FileReader(filePath)) {
            int data;
            // Read character by character until the end of the file (-1)
            while ((data = fr.read()) != -1) {
                System.out.print((char) data);
            }
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
