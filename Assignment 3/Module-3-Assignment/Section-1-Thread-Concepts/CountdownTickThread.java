// Q4: Create a program where:
// - One thread prints a countdown from 10 to 1 with a 1-second delay.
// - Another thread simultaneously prints "Tick..." every half a second.

class CountdownThread extends Thread {
    public void run() {
        for (int i = 10; i >= 1; i--) {
            System.out.println("Countdown: " + i);
            try {
                Thread.sleep(1000); // 1000ms = 1 second delay
            } catch (InterruptedException e) {
                System.out.println("Countdown interrupted.");
            }
        }
    }
}

class TickThread extends Thread {
    public void run() {
        // Run 20 times (every 0.5s) to cover the full 10-second countdown (10 / 0.5 = 20)
        for (int i = 0; i < 20; i++) {
            System.out.println("Tick...");
            try {
                Thread.sleep(500); // 500ms = half a second delay
            } catch (InterruptedException e) {
                System.out.println("Tick interrupted.");
            }
        }
    }
}

public class CountdownTickThread {
    public static void main(String[] args) {
        System.out.println("Starting Countdown and Tick threads...\n");
        
        // Create instances of both threads
        CountdownThread countdown = new CountdownThread();
        TickThread tick = new TickThread();

        // Start both threads to run simultaneously
        countdown.start();
        tick.start();
    }
}
