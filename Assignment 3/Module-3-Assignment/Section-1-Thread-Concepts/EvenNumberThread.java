// Q1: Create a thread by extending the Thread class that prints even numbers from 2 to 20 with a 500ms delay between each number.

public class EvenNumberThread extends Thread {
    @Override
    public void run() {
        // Loop from 2 to 20, incrementing by 2 to get even numbers
        for (int i = 2; i <= 20; i += 2) {
            System.out.println("Even Number: " + i);
            try {
                // Introduce a 500ms delay between each print
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting EvenNumberThread...");
        // Create an instance of the thread
        EvenNumberThread thread = new EvenNumberThread();
        // Start the thread, which automatically calls the run() method
        thread.start();
    }
}
