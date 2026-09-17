// Q8: Deadlock Example with Dining Philosophers
// Simulate a simple Dining Philosophers problem to demonstrate a deadlock.

public class DiningPhilosophersDeadlock {
    public static void main(String[] args) {
        // Two shared resources representing chopsticks
        final Object chopstick1 = new Object();
        final Object chopstick2 = new Object();

        // Philosopher 1 tries to grab Chopstick 1 then Chopstick 2
        Thread philosopher1 = new Thread(() -> {
            synchronized (chopstick1) {
                System.out.println("Philosopher 1 acquired Chopstick 1.");
                try { Thread.sleep(100); } catch (Exception e) {}
                
                System.out.println("Philosopher 1 waiting for Chopstick 2...");
                synchronized (chopstick2) {
                    System.out.println("Philosopher 1 acquired Chopstick 2 and is eating.");
                }
            }
        });

        // Philosopher 2 tries to grab Chopstick 2 then Chopstick 1
        // DEADLOCK EXPLANATION: 
        // Philosopher 1 holds Chopstick 1 and is waiting for Chopstick 2.
        // Philosopher 2 holds Chopstick 2 and is waiting for Chopstick 1.
        // Neither can proceed because they are waiting on resources held by each other.
        Thread philosopher2 = new Thread(() -> {
            synchronized (chopstick2) {
                System.out.println("Philosopher 2 acquired Chopstick 2.");
                try { Thread.sleep(100); } catch (Exception e) {}
                
                System.out.println("Philosopher 2 waiting for Chopstick 1...");
                synchronized (chopstick1) {
                    System.out.println("Philosopher 2 acquired Chopstick 1 and is eating.");
                }
            }
        });

        // Start both threads
        philosopher1.start();
        philosopher2.start();

        // In order to not let the program hang indefinitely, the main thread will wait
        // to let the deadlock occur, and then forcibly exit the program.
        try {
            Thread.sleep(1500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nDEADLOCK OCCURRED! Both threads are stuck waiting forever.");
        System.out.println("Exiting program gracefully so the terminal does not hang...");
        System.exit(0); // Force termination
    }
}
