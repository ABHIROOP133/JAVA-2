// Q16: Writing to a File Using Character Stream (FileWriter)
import java.io.FileWriter;
import java.io.IOException;

public class CharStreamWrite {
    public static void main(String[] args) {
        String filePath = "example.txt";
        String content = "This is a string written using a FileWriter (Character Stream).";

        // try-with-resources ensures the FileWriter is closed
        try (FileWriter fw = new FileWriter(filePath)) {
            // Write string directly (no byte conversion needed for Character streams)
            fw.write(content);
            System.out.println("Confirmation: String successfully written to " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
