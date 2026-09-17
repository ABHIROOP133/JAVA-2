// Q19: Listing All Files in a Directory
import java.io.File;
import java.util.Scanner;

public class ListDirectoryFiles {
    public static void main(String[] args) {
        // 1. Take a directory path from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a directory path to list its files: ");
        String dirPath = scanner.nextLine();
        scanner.close();

        try {
            File directory = new File(dirPath);

            // 3. Handle invalid directory paths appropriately
            if (!directory.exists()) {
                System.out.println("Error: The specified path does not exist.");
            } else if (!directory.isDirectory()) {
                System.out.println("Error: The specified path is a file, not a directory.");
            } else {
                // 2. List all files in that directory
                System.out.println("\nListing contents of directory: " + dirPath);
                File[] files = directory.listFiles();
                
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            System.out.println("[DIR]  " + file.getName());
                        } else {
                            System.out.println("[FILE] " + file.getName());
                        }
                    }
                } else {
                    System.out.println("The directory is empty or cannot be read.");
                }
            }
        } catch (Exception e) {
            // 4. Handle exceptions properly
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
