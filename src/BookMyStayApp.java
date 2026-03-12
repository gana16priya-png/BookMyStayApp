import java.util.*;

/**
 * ============================================================
 * CUSTOM EXCEPTION - InvalidBookingException
 * ============================================================
 */

class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
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
        availability.put("Single", 2);
        availability.put("Double", 1);
        availability.put("Suite", 1);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, -1);
    }

    public void decrease(String type) {
        availability.put(type, availability.get(type) - 1);
    }

    public Set<String> getRoomTypes() {
        return availability.keySet();
    }
}


/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 * UC9: Validation + Exception Handling
 */

public class BookMyStayApp {

    public static void validateBooking(String roomType,
                                       RoomInventory inventory)
            throws InvalidBookingException {

        // Validate room type
        if (!inventory.getRoomTypes().contains(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        // Validate availability
        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for "
                    + roomType);
        }
    }

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Sample inputs (valid + invalid)
        String[] bookingInputs = {"Single", "Suite", "Deluxe", "Single"};

        System.out.println("Processing Bookings...\n");

        for (String roomType : bookingInputs) {

            try {

                validateBooking(roomType, inventory);

                inventory.decrease(roomType);

                System.out.println("Booking confirmed for "
                        + roomType);

            } catch (InvalidBookingException e) {

                System.out.println("Booking failed: "
                        + e.getMessage());
            }
        }

        System.out.println("\nSystem running safely.");
    }
}