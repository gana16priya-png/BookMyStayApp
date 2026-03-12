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

    public void display() {
        System.out.println("Guest: " + guestName +
                " | Room Type: " + roomType +
                " | Room ID: " + roomId);
    }
}


/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 * UC8: Booking History + Reporting
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        // Booking history list (chronological order)
        List<Reservation> bookingHistory = new ArrayList<>();

        // Simulated confirmed bookings (from UC6)
        bookingHistory.add(new Reservation("Aarav", "Single", "S45"));
        bookingHistory.add(new Reservation("Diya", "Double", "D21"));
        bookingHistory.add(new Reservation("Rohan", "Suite", "S89"));

        System.out.println("===== BOOKING HISTORY =====\n");

        // Display history
        for (Reservation r : bookingHistory) {
            r.display();
        }

        // ==========================
        // REPORT SECTION
        // ==========================

        System.out.println("\n===== BOOKING SUMMARY REPORT =====\n");

        Map<String, Integer> summary = new HashMap<>();

        for (Reservation r : bookingHistory) {
            summary.put(r.roomType,
                    summary.getOrDefault(r.roomType, 0) + 1);
        }

        for (String type : summary.keySet()) {
            System.out.println(type + " Rooms Booked: "
                    + summary.get(type));
        }

        System.out.println("\nReport generated successfully.");
    }
}