// Q7: Synchronized Block
// Create an inventory management program where multiple threads decrease the stock count of a product.

public class InventoryManagement {
    private int stockCount = 5;
    // Shared lock object to synchronize the stock update
    private final Object lock = new Object();

    public void decreaseStock(String threadName, int amount) {
        // This print statement is outside the synchronized block to show threads entering
        System.out.println(threadName + " checking stock...");
        
        // Synchronized block to protect the critical section (stock update)
        synchronized (lock) {
            if (stockCount >= amount) {
                System.out.println(threadName + " is purchasing " + amount + " items.");
                stockCount -= amount;
                System.out.println("Purchase successful! Stock remaining: " + stockCount);
            } else {
                System.out.println(threadName + " purchase failed. Insufficient stock.");
            }
        }
    }

    public static void main(String[] args) {
        InventoryManagement inventory = new InventoryManagement();

        Runnable task = () -> {
            inventory.decreaseStock(Thread.currentThread().getName(), 2);
        };

        Thread t1 = new Thread(task, "Thread-A");
        Thread t2 = new Thread(task, "Thread-B");
        Thread t3 = new Thread(task, "Thread-C");

        t1.start();
        t2.start();
        t3.start();
        
        try {
            t1.join(); 
            t2.join(); 
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Display final stock
        System.out.println("Final inventory stock: " + inventory.stockCount);
    }
}
