// Q3: Create a program that creates two threads.
// - Thread 1 prints "Thread 1" every 1 second.
// - Thread 2 prints "Thread 2" every 2 seconds.

class ThreadOne implements Runnable {
    public void run() {
        // We run it for 5 iterations so the program finishes eventually
        for (int i = 0; i < 5; i++) { 
            System.out.println("Thread 1");
            try {
                Thread.sleep(1000); // 1000 milliseconds = 1 second delay
            } catch (InterruptedException e) {
                System.out.println("Thread 1 interrupted");
            }
        }
    }
}

class ThreadTwo implements Runnable {
    public void run() {
        // We run it for 3 iterations to roughly match Thread 1's timeline
        for (int i = 0; i < 3; i++) { 
            System.out.println("Thread 2");
            try {
                Thread.sleep(2000); // 2000 milliseconds = 2 seconds delay
            } catch (InterruptedException e) {
                System.out.println("Thread 2 interrupted");
            }
        }
    }
}

public class TwoTimersThread {
    public static void main(String[] args) {
        System.out.println("Starting Two Timers...");
        
        // Create instances of the Runnable classes
        Runnable task1 = new ThreadOne();
        Runnable task2 = new ThreadTwo();

        // Create threads from the Runnable tasks
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        // Start both threads simultaneously
        t1.start();
        t2.start();
    }
}
