// Q21: Deleting a File
import java.io.File;
import java.io.IOException;

public class FileDeletion {
    public static void main(String[] args) {
        String filename = "fileToDelete.txt";
        File file = new File(filename);

        // First, let's create a temporary file to demonstrate deletion
        try {
            if (file.createNewFile()) {
                System.out.println("Created a temporary file: " + filename);
            }
        } catch (IOException e) {
            System.out.println("Could not create temp file.");
        }

        // Requirements:
        // 1. Check whether the file exists first.
        if (file.exists()) {
            System.out.println("File exists. Attempting to delete...");
            
            // 2. Delete the file using File.delete().
            boolean isDeleted = file.delete();
            
            // 3. Display whether deletion was successful.
            if (isDeleted) {
                System.out.println("Success! The file was deleted.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            // 4. Handle the case where the file does not exist.
            System.out.println("Cannot delete: The file '" + filename + "' does not exist.");
        }
    }
}
