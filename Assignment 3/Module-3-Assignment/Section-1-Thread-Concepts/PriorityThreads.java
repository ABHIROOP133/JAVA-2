// Q5: Create a Java program that creates three threads: Worker-1, Worker-2, Worker-3
// Assign different priorities to the three threads and print messages from each thread showing their execution order.

class WorkerThread extends Thread {
    // Constructor to assign a name to the thread
    public WorkerThread(String name) {
        super(name); 
    }

    public void run() {
        for (int i = 1; i <= 3; i++) {
            // Print the thread's name and its priority
            System.out.println(getName() + " is running with priority " + getPriority());
            try {
                // Short sleep to allow the scheduler to switch between threads
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
        }
    }
}

public class PriorityThreads {
    public static void main(String[] args) {
        // Create three worker threads
        WorkerThread worker1 = new WorkerThread("Worker-1");
        WorkerThread worker2 = new WorkerThread("Worker-2");
        WorkerThread worker3 = new WorkerThread("Worker-3");

        // Assign different priorities to the threads
        // Thread priorities in Java range from 1 (MIN_PRIORITY) to 10 (MAX_PRIORITY)
        worker1.setPriority(Thread.MIN_PRIORITY);   // Priority 1
        worker2.setPriority(Thread.NORM_PRIORITY);  // Priority 5
        worker3.setPriority(Thread.MAX_PRIORITY);   // Priority 10

        // Important explanation for the assignment
        System.out.println("--- Thread Priority Demonstration ---");
        System.out.println("Note: Thread priority does NOT guarantee a strict execution order.");
        System.out.println("It merely serves as a hint to the operating system's thread scheduler.");
        System.out.println("A higher priority thread is more likely to be executed earlier, but it is not guaranteed.\n");

        // Start the threads
        worker1.start();
        worker2.start();
        worker3.start();
    }
}
