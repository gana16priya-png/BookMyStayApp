import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================
 * CLASS - Reservation
 * ============================================================
 * Represents guest booking request
 */

class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}


/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 * UC5: Booking Request Queue using FIFO
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        // Create Queue
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Add booking requests (arrival order)
        bookingQueue.add(new Reservation("Aarav", "Single"));
        bookingQueue.add(new Reservation("Diya", "Double"));
        bookingQueue.add(new Reservation("Rohan", "Suite"));
        bookingQueue.add(new Reservation("Meera", "Single"));

        System.out.println("Booking Requests in Queue (FIFO Order):\n");

        // Display Queue (NO PROCESSING)
        for (Reservation r : bookingQueue) {
            r.display();
        }
    }
}