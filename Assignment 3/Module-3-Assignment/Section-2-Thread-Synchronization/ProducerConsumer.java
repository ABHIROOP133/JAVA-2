// Q9: Producer Consumer
// Implement a producer-consumer scenario using wait() and notify().

import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity = 3; // Max capacity of the buffer

    public synchronized void produce(int value) throws InterruptedException {
        // Wait if buffer is full
        while (queue.size() == capacity) {
            System.out.println("Buffer full. Producer is waiting...");
            wait(); // Releases the lock and waits for notify()
        }
        
        queue.add(value);
        System.out.println("Produced: " + value);
        
        // Notify the consumer that an item is now available
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        // Wait if buffer is empty
        while (queue.isEmpty()) {
            System.out.println("Buffer empty. Consumer is waiting...");
            wait(); // Releases the lock and waits for notify()
        }
        
        int value = queue.poll();
        System.out.println("Consumed: " + value);
        
        // Notify the producer that space is now available
        notify();
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    buffer.produce(i);
                    Thread.sleep(100); // Produce quickly to fill buffer
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    buffer.consume();
                    Thread.sleep(800); // Consume slowly to trigger Producer waiting
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}
