import java.util.*;

/**
 * ============================================================
 * CLASS - Reservation
 * ============================================================
 */

class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}


/**
 * ============================================================
 * CLASS - RoomInventory (Thread Safe)
 * ============================================================
 */

class RoomInventory {

    private Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single", 2);
        availability.put("Double", 1);
        availability.put("Suite", 1);
    }

    // Critical Section (Thread Safe)
    public synchronized boolean allocate(String type) {

        if (availability.getOrDefault(type, 0) > 0) {

            availability.put(type, availability.get(type) - 1);
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println("\nFinal Inventory:");
        for (String type : availability.keySet()) {
            System.out.println(type + ": " + availability.get(type));
        }
    }
}


/**
 * ============================================================
 * CLASS - BookingProcessor (Thread)
 * ============================================================
 */

class BookingProcessor extends Thread {

    private Queue<Reservation> queue;
    private RoomInventory inventory;

    public BookingProcessor(Queue<Reservation> queue,
                            RoomInventory inventory) {

        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {

        while (true) {

            Reservation r;

            synchronized (queue) {

                if (queue.isEmpty())
                    break;

                r = queue.poll();
            }

            if (inventory.allocate(r.roomType)) {

                System.out.println(Thread.currentThread().getName()
                        + " confirmed booking for "
                        + r.guestName + " (" + r.roomType + ")");

            } else {

                System.out.println(Thread.currentThread().getName()
                        + " failed booking for "
                        + r.guestName);
            }
        }
    }
}


/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 * UC11: Concurrent Booking Simulation
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Aarav", "Single"));
        bookingQueue.add(new Reservation("Diya", "Double"));
        bookingQueue.add(new Reservation("Rohan", "Suite"));
        bookingQueue.add(new Reservation("Meera", "Single"));

        RoomInventory inventory = new RoomInventory();

        // Multiple threads
        BookingProcessor t1 =
                new BookingProcessor(bookingQueue, inventory);

        BookingProcessor t2 =
                new BookingProcessor(bookingQueue, inventory);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inventory.display();
    }
}