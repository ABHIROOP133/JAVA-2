// Q9: Odd/Even Alternate Printing
// Two threads print numbers from 1 to 20 alternately using wait() and notify().

class NumberPrinter {
    private boolean isEvenTurn = false; // Start with the odd number

    public synchronized void printOdd(int number) throws InterruptedException {
        // Wait if it is even's turn
        while (isEvenTurn) {
            wait();
        }
        System.out.print(number + " ");
        
        // Change turn and notify the even thread
        isEvenTurn = true;
        notify();
    }

    public synchronized void printEven(int number) throws InterruptedException {
        // Wait if it is odd's turn
        while (!isEvenTurn) {
            wait();
        }
        System.out.print(number + " ");
        
        // Change turn and notify the odd thread
        isEvenTurn = false;
        notify();
    }
}

public class OddEvenThreads {
    public static void main(String[] args) {
        NumberPrinter printer = new NumberPrinter();

        // Thread to print odd numbers
        Thread oddThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 20; i += 2) {
                    printer.printOdd(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread to print even numbers
        Thread evenThread = new Thread(() -> {
            try {
                for (int i = 2; i <= 20; i += 2) {
                    printer.printEven(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        oddThread.start();
        evenThread.start();
        
        // Wait for both threads to finish before exiting
        try {
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Print a newline at the end
    }
}
