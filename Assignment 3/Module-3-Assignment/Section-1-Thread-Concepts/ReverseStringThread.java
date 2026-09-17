// Q2: Create a thread by implementing the Runnable interface that takes the string "MULTITHREADING" and prints its characters in reverse order one by one.

public class ReverseStringThread implements Runnable {
    private String text;

    // Constructor to initialize the string
    public ReverseStringThread(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        // Loop from the last character index down to 0
        for (int i = text.length() - 1; i >= 0; i--) {
            System.out.print(text.charAt(i) + " ");
            try {
                // A small 300ms delay to clearly show the characters printing one by one
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
            }
        }
        System.out.println(); // Print a newline at the end
    }

    public static void main(String[] args) {
        String targetWord = "MULTITHREADING";
        System.out.println("Reversing string: " + targetWord);
        
        // 1. Create an instance of the class that implements Runnable
        ReverseStringThread runnableObj = new ReverseStringThread(targetWord);
        
        // 2. Pass the Runnable instance to a new Thread object
        Thread thread = new Thread(runnableObj);
        
        // 3. Start the thread
        thread.start();
    }
}
