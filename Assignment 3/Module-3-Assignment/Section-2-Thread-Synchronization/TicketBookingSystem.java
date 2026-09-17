// Q6: Synchronized Method
// Create a ticket booking system where multiple users (threads) attempt to book tickets simultaneously.

public class TicketBookingSystem {
    private int availableTickets = 10;

    // The 'synchronized' keyword ensures that only one thread can execute this method at a time
    // This prevents overselling tickets (race condition)
    public synchronized void bookTicket(String userName, int ticketsToBook) {
        System.out.println(userName + " is attempting to book " + ticketsToBook + " tickets...");
        if (availableTickets >= ticketsToBook) {
            System.out.println(userName + " successfully booked " + ticketsToBook + " tickets!");
            availableTickets -= ticketsToBook;
        } else {
            System.out.println(userName + " failed to book. Not enough tickets. Available: " + availableTickets);
        }
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();

        // Runnable that attempts to book tickets
        Runnable bookingTask = () -> {
            String threadName = Thread.currentThread().getName();
            system.bookTicket(threadName, 3);
        };

        // Create multiple user threads
        Thread user1 = new Thread(bookingTask, "User 1");
        Thread user2 = new Thread(bookingTask, "User 2");
        Thread user3 = new Thread(bookingTask, "User 3");
        Thread user4 = new Thread(bookingTask, "User 4");

        // Start all threads simultaneously
        user1.start();
        user2.start();
        user3.start();
        user4.start();

        // Wait for all threads to finish using join()
        try {
            user1.join();
            user2.join();
            user3.join();
            user4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final remaining tickets: " + system.getAvailableTickets());
    }
}
