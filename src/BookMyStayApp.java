import java.io.*;
import java.util.*;

/**
 * ============================================================
 * CLASS - Reservation (Serializable)
 * ============================================================
 */

class Reservation implements Serializable {

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
                " | Room: " + roomType +
                " | ID: " + roomId);
    }
}


/**
 * ============================================================
 * CLASS - RoomInventory (Serializable)
 * ============================================================
 */

class RoomInventory implements Serializable {

    Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single", 2);
        availability.put("Double", 1);
        availability.put("Suite", 1);
    }

    public void display() {
        System.out.println("\nInventory:");
        for (String type : availability.keySet()) {
            System.out.println(type + ": " + availability.get(type));
        }
    }
}


/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 * UC12: Persistence + Recovery
 */

public class BookMyStayApp {

    static final String FILE_NAME = "system_state.dat";

    public static void saveState(List<Reservation> bookings,
                                 RoomInventory inventory) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(FILE_NAME))) {

            out.writeObject(bookings);
            out.writeObject(inventory);

            System.out.println("System state saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving system state.");
        }
    }

    public static void loadState(List<Reservation> bookings,
                                 RoomInventory inventory) {

        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(FILE_NAME))) {

            bookings.clear();
            bookings.addAll((List<Reservation>) in.readObject());

            RoomInventory restored =
                    (RoomInventory) in.readObject();

            inventory.availability = restored.availability;

            System.out.println("System state restored successfully.");

        } catch (Exception e) {

            System.out.println("No previous state found. Starting fresh.");
        }
    }

    public static void main(String[] args) {

        List<Reservation> bookingHistory = new ArrayList<>();
        RoomInventory inventory = new RoomInventory();

        // Load previous state
        loadState(bookingHistory, inventory);

        // Add sample booking
        bookingHistory.add(new Reservation("Aarav",
                "Single", "S45"));

        // Display current state
        System.out.println("\n===== CURRENT BOOKINGS =====\n");
        for (Reservation r : bookingHistory) {
            r.display();
        }

        inventory.display();

        // Save before shutdown
        saveState(bookingHistory, inventory);
    }
}