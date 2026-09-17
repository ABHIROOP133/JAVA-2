// Q14: Writing to a File Using Byte Stream (FileOutputStream)
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamWrite {
    public static void main(String[] args) {
        String filePath = "output.txt";
        // As requested exactly by the assignment
        String content = "Java I/O Streams Example";

        // try-with-resources ensures the stream is closed automatically
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            // Convert string to a byte array and write it
            fos.write(content.getBytes());
            System.out.println("Successfully wrote exactly 'Java I/O Streams Example' to " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
