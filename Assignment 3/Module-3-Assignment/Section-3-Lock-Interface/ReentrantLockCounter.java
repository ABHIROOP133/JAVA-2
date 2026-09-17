import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// QUESTION 11: ReentrantLock Counter
public class ReentrantLockCounter {
    // 1. Shared counter without lock
    private int unsafeCounter = 0;
    
    // 2. Shared counter with lock
    private int safeCounter = 0;
    private final Lock lock = new ReentrantLock();

    // Increment WITHOUT lock (Unsafe)
    // -------------------------------------------------------------
    // EXPLANATION OF RACE CONDITION:
    // Incrementing (unsafeCounter++) involves three separate steps:
    // 1. Read the current value of the variable.
    // 2. Add 1 to the value.
    // 3. Write the new value back to the variable.
    // When multiple threads do this simultaneously, their reads and writes 
    // can interleave. For example, if two threads read '5' at the same time, 
    // both will write '6'. One increment operation is lost.
    public void incrementUnsafe() {
        unsafeCounter++;
    }

    // Increment WITH ReentrantLock (Safe)
    // -------------------------------------------------------------
    // EXPLANATION OF REENTRANTLOCK SAFETY:
    // lock.lock() creates a mutually exclusive boundary (critical section).
    // Only one thread can enter this section at a time. If another thread 
    // arrives while it is locked, it will wait until lock.unlock() is called.
    // This ensures that the read-add-write sequence happens atomically.
    public void incrementSafe() {
        lock.lock(); // Acquire the lock
        try {
            // Critical section
            safeCounter++;
        } finally {
            // Using try-finally ensures that unlock() is ALWAYS called, 
            // even if an exception occurs inside the try block.
            lock.unlock(); // Release the lock safely
        }
    }

    public int getUnsafeCounter() { return unsafeCounter; }
    public int getSafeCounter() { return safeCounter; }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockCounter counterObj = new ReentrantLockCounter();
        
        // Define tasks where each thread increments 10,000 times
        Runnable unsafeTask = () -> {
            for (int i = 0; i < 10000; i++) {
                counterObj.incrementUnsafe();
            }
        };

        Runnable safeTask = () -> {
            for (int i = 0; i < 10000; i++) {
                counterObj.incrementSafe();
            }
        };

        // 3. Demonstrate WITHOUT lock
        Thread u1 = new Thread(unsafeTask);
        Thread u2 = new Thread(unsafeTask);
        
        System.out.println("Starting threads WITHOUT lock (Expected: 20000)...");
        u1.start(); u2.start();
        u1.join(); u2.join(); // Wait for completion
        
        System.out.println("Unsafe Counter Result: " + counterObj.getUnsafeCounter());
        System.out.println("-> Notice how the result is less than 20000 due to race conditions!\n");

        // 4. Demonstrate WITH ReentrantLock
        Thread s1 = new Thread(safeTask);
        Thread s2 = new Thread(safeTask);
        
        System.out.println("Starting threads WITH ReentrantLock (Expected: 20000)...");
        s1.start(); s2.start();
        s1.join(); s2.join(); // Wait for completion
        
        System.out.println("Safe Counter Result: " + counterObj.getSafeCounter());
        System.out.println("-> Notice how ReentrantLock prevented race conditions and the result is exactly 20000!");
    }
}
