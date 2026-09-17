// Q22: Random Access File
import java.io.RandomAccessFile;
import java.io.IOException;

public class RandomAccessFileExample {
    public static void main(String[] args) {
        String filename = "random_access.txt";

        // 1. Open/create a file in read-write mode ("rw")
        try (RandomAccessFile raf = new RandomAccessFile(filename, "rw")) {
            
            // 2. Write some data at the beginning of the file
            // Setting file length to 0 to clear it if it exists from previous runs
            raf.setLength(0); 
            raf.writeBytes("Hello World! This is a test.");
            System.out.println("Initial content written to file.");

            // 3. Use seek() to move to a specific position
            // EXPLANATION OF seek(): 
            // seek(long pos) sets the file-pointer offset, measured from the
            // beginning of the file, at which the next read or write occurs.
            // "Hello World!" -> The 'W' in "World" starts at index 6.
            raf.seek(6);
            System.out.println("Moved file pointer to position 6 using seek(6).");
            
            // 4. Overwrite part of the existing data
            // We overwrite "World" with "Java "
            raf.writeBytes("Java ");
            System.out.println("Overwrote data starting at position 6.");

            // 5. Read and display the resulting contents
            // Move pointer back to the very beginning (0) to read everything
            raf.seek(0);
            String finalContent = raf.readLine();
            System.out.println("\nFinal File Content:");
            System.out.println(finalContent);
            
            // 6. Demonstrate that RandomAccessFile allows reading and writing at specific positions
            System.out.println("\n-> Demonstration successful! We wrote data, jumped to the middle of the file");
            System.out.println("-> using seek(), overwrote specific bytes, and read the file from the start.");

        } catch (IOException e) {
            System.out.println("An I/O Error occurred: " + e.getMessage());
        }
    }
}
