// Q20: Copying a File
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {
        // Define source and destination files
        String sourceFile = "source.txt";
        String destFile = "destination.txt";

        // Create a dummy source file first so we have something to copy
        try (FileOutputStream dummyFos = new FileOutputStream(sourceFile)) {
            dummyFos.write("This is the original text in the source file.".getBytes());
        } catch (IOException e) {
            System.out.println("Error creating dummy source file.");
        }

        System.out.println("Attempting to copy from '" + sourceFile + "' to '" + destFile + "'...");

        // Copy the contents using a byte buffer
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destFile)) {
             
            byte[] buffer = new byte[1024]; // 1KB byte buffer
            int bytesRead;

            // Read up to 1024 bytes at a time and write them to the destination
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            
            // Display a successful-copy message
            System.out.println("Success! File has been copied.");
            
        } catch (IOException e) {
            // Handle IOException
            System.out.println("An I/O Error occurred during copy: " + e.getMessage());
        }

        // Verify that the destination file was created
        java.io.File verification = new java.io.File(destFile);
        if (verification.exists()) {
            System.out.println("Verification passed: Destination file exists on disk.");
        }
    }
}
