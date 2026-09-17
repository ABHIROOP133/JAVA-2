// Q18: File Existence Check
import java.io.File;
import java.io.IOException;

public class FileExistenceCheck {
    public static void main(String[] args) {
        String filename = "test_existence.txt";
        File file = new File(filename);

        try {
            // 1. Check whether a specified file exists
            if (file.exists()) {
                System.out.println("The file '" + filename + "' already exists.");
            } else {
                System.out.println("The file '" + filename + "' does not exist.");
                
                // 2. If the file does not exist, create the file
                boolean isCreated = file.createNewFile();
                
                // 3. Display an appropriate message
                if (isCreated) {
                    System.out.println("Successfully created the new file: '" + filename + "'.");
                } else {
                    System.out.println("Failed to create the file.");
                }
            }
        } catch (IOException e) {
            // 4. Handle exceptions appropriately
            System.out.println("An I/O error occurred: " + e.getMessage());
        }
    }
}
