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
 * CLASS - RoomInventory
 * ============================================================
 */

class RoomInventory {

    private HashMap<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single", 2);
        availability.put("Double", 1);
        availability.put("Suite", 1);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    public void decrease(String type) {
        availability.put(type, availability.get(type) - 1);
    }
}


/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 * UC6: Booking Confirmation + Allocation
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        // Queue (UC5 reused)
        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Aarav", "Single"));
        bookingQueue.add(new Reservation("Diya", "Double"));
        bookingQueue.add(new Reservation("Rohan", "Suite"));
        bookingQueue.add(new Reservation("Meera", "Single"));

        RoomInventory inventory = new RoomInventory();

        // Prevent duplicate room IDs
        Set<String> allocatedRoomIds = new HashSet<>();

        // Track allocations by type
        HashMap<String, Set<String>> allocations = new HashMap<>();

        System.out.println("Processing Booking Requests...\n");

        while (!bookingQueue.isEmpty()) {

            Reservation r = bookingQueue.poll(); // FIFO

            if (inventory.getAvailability(r.roomType) > 0) {

                // Generate unique room ID
                String roomId;

                do {
                    roomId = r.roomType.substring(0, 1).toUpperCase()
                            + (int) (Math.random() * 100);
                } while (allocatedRoomIds.contains(roomId));

                // Store ID
                allocatedRoomIds.add(roomId);

                allocations.putIfAbsent(r.roomType, new HashSet<>());
                allocations.get(r.roomType).add(roomId);

                // Update inventory
                inventory.decrease(r.roomType);

                System.out.println("Booking Confirmed → Guest: "
                        + r.guestName + " | Room: " + roomId);

            } else {

                System.out.println("Booking Failed → Guest: "
                        + r.guestName + " (No rooms available)");
            }
        }
    }
}