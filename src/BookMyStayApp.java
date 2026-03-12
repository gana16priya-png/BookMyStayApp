import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * CLASS - Room (Domain Model)
 * ============================================================
 * Stores room details like price, beds, size.
 */

class Room {

    String type;
    int beds;
    int size;
    double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayDetails(int availableRooms) {

        System.out.println(type + " Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println();
    }
}


/**
 * ============================================================
 * CLASS - RoomInventory (UC3 reused)
 * ============================================================
 */

class RoomInventory {

    private HashMap<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}


/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 * UC4: Room Search (Read-Only Access)
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Domain model objects
        Room single = new Room("Single", 1, 250, 1500.0);
        Room dbl = new Room("Double", 2, 400, 2500.0);
        Room suite = new Room("Suite", 3, 750, 5000.0);

        System.out.println("Available Rooms (Search Result)\n");

        // READ-ONLY SEARCH LOGIC
        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get("Single") > 0)
            single.displayDetails(availability.get("Single"));

        if (availability.get("Double") > 0)
            dbl.displayDetails(availability.get("Double"));

        if (availability.get("Suite") > 0)
            suite.displayDetails(availability.get("Suite"));
    }
}