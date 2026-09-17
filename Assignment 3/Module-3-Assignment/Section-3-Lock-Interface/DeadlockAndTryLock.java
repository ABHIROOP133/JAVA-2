import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

// QUESTION 12: Deadlock and tryLock()
public class DeadlockAndTryLock {
    
    // 1. Create lock1 and lock2
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    // -------------------------------------------------------------
    // EXPLANATION OF HOW tryLock() PREVENTS INDEFINITE WAITING:
    // The standard lock.lock() method will block the thread forever until 
    // the lock is available. This causes deadlocks when threads wait on each other.
    // 
    // lock.tryLock(time, unit) attempts to acquire the lock but will give up 
    // after the specified timeout. If it times out (returns false), the thread 
    // can safely back off, release any locks it currently holds, and avoid 
    // a deadlock situation from becoming permanent.
    // -------------------------------------------------------------

    // Thread 1 acquires lock1 and then attempts lock2
    public void tryAcquireMethod1(String threadName) {
        boolean acquiredLock1 = false;
        boolean acquiredLock2 = false;

        try {
            System.out.println(threadName + " attempting to acquire Lock 1...");
            acquiredLock1 = lock1.tryLock(2, TimeUnit.SECONDS);
            
            if (acquiredLock1) {
                System.out.println(threadName + " acquired Lock 1!");
                
                // Sleep to ensure Thread 2 has time to acquire Lock 2, creating the deadlock scenario
                Thread.sleep(500); 
                
                System.out.println(threadName + " attempting to acquire Lock 2...");
                // This is where deadlock would happen if we used regular lock.lock()
                acquiredLock2 = lock2.tryLock(2, TimeUnit.SECONDS);
                
                if (acquiredLock2) {
                    System.out.println(threadName + " acquired Lock 2! Executing critical section.");
                } else {
                    // Fix: If the second lock cannot be acquired, back off!
                    System.out.println(threadName + " COULD NOT acquire Lock 2. Timeout reached. Backing off to prevent deadlock.");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Safe release: Release locks only if they were acquired, in reverse order
            if (acquiredLock2) {
                lock2.unlock();
                System.out.println(threadName + " released Lock 2.");
            }
            if (acquiredLock1) {
                lock1.unlock();
                System.out.println(threadName + " released Lock 1.");
            }
        }
    }

    // Thread 2 acquires lock2 and then attempts lock1 (Opposite order)
    public void tryAcquireMethod2(String threadName) {
        boolean acquiredLock2 = false;
        boolean acquiredLock1 = false;

        try {
            System.out.println(threadName + " attempting to acquire Lock 2...");
            acquiredLock2 = lock2.tryLock(2, TimeUnit.SECONDS);
            
            if (acquiredLock2) {
                System.out.println(threadName + " acquired Lock 2!");
                
                // Sleep to ensure Thread 1 has time to acquire Lock 1, creating the deadlock scenario
                Thread.sleep(500);
                
                System.out.println(threadName + " attempting to acquire Lock 1...");
                // This is where deadlock would happen if we used regular lock.lock()
                acquiredLock1 = lock1.tryLock(2, TimeUnit.SECONDS);
                
                if (acquiredLock1) {
                    System.out.println(threadName + " acquired Lock 1! Executing critical section.");
                } else {
                    // Fix: If the second lock cannot be acquired, back off!
                    System.out.println(threadName + " COULD NOT acquire Lock 1. Timeout reached. Backing off to prevent deadlock.");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Safe release: Release locks only if they were acquired, in reverse order
            if (acquiredLock1) {
                lock1.unlock();
                System.out.println(threadName + " released Lock 1.");
            }
            if (acquiredLock2) {
                lock2.unlock();
                System.out.println(threadName + " released Lock 2.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("--- DEADLOCK SCENARIO HANDLED BY tryLock() ---");
        System.out.println("Thread 1 will grab Lock 1 and wait for Lock 2.");
        System.out.println("Thread 2 will grab Lock 2 and wait for Lock 1.");
        System.out.println("Instead of hanging forever, they will timeout and back off.\n");

        DeadlockAndTryLock example = new DeadlockAndTryLock();
        
        // 2. Create two threads
        Thread t1 = new Thread(() -> {
            example.tryAcquireMethod1("Thread 1");
        });

        Thread t2 = new Thread(() -> {
            example.tryAcquireMethod2("Thread 2");
        });

        // Start threads simultaneously to trigger the opposite order locking scenario
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nProgram finished successfully without hanging!");
    }
}
