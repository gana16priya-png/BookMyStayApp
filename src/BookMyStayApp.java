import java.util.*;

/**
 * ============================================================
 * CLASS - Reservation
 * ============================================================
 */

class Reservation {

    String guestName;
    String roomType;
    String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}


/**
 * ============================================================
 * CLASS - RoomInventory
 * ============================================================
 */

class RoomInventory {

    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single", 0);
        availability.put("Double", 0);
        availability.put("Suite", 0);
    }

    public void increase(String type) {
        availability.put(type, availability.get(type) + 1);
    }

    public void display() {
        System.out.println("Current Inventory:");
        for (String type : availability.keySet()) {
            System.out.println(type + ": " + availability.get(type));
        }
    }
}


/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 * UC10: Booking Cancellation + Rollback
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        // Confirmed bookings (from UC6/UC8)
        List<Reservation> bookingHistory = new ArrayList<>();

        bookingHistory.add(new Reservation("Aarav", "Single", "S45"));
        bookingHistory.add(new Reservation("Diya", "Double", "D21"));

        RoomInventory inventory = new RoomInventory();

        // Stack for rollback
        Stack<String> rollbackStack = new Stack<>();

        System.out.println("Cancelling booking for Aarav...\n");

        String cancelGuest = "Aarav";

        Iterator<Reservation> iterator = bookingHistory.iterator();

        boolean found = false;

        while (iterator.hasNext()) {

            Reservation r = iterator.next();

            if (r.guestName.equals(cancelGuest)) {

                // Push released room ID
                rollbackStack.push(r.roomId);

                // Restore inventory
                inventory.increase(r.roomType);

                // Remove booking
                iterator.remove();

                found = true;

                System.out.println("Cancellation successful for "
                        + cancelGuest);
                break;
            }
        }

        if (!found) {
            System.out.println("Cancellation failed: Booking not found.");
        }

        System.out.println("\nReleased Room IDs (Rollback Stack): "
                + rollbackStack);

        System.out.println();
        inventory.display();
    }
}