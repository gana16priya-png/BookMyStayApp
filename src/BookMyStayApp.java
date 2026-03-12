import java.util.*;

/**
 * ============================================================
 * CLASS - AddOnService
 * ============================================================
 */

class AddOnService {

    String name;
    double price;

    public AddOnService(String name, double price) {
        this.name = name;
        this.price = price;
    }
}


/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 * UC7: Add-On Services Model
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        // Existing confirmed reservation IDs (from UC6)
        List<String> confirmedReservations = Arrays.asList("S45", "D21", "S89");

        // Map reservation → services
        Map<String, List<AddOnService>> serviceMap = new HashMap<>();

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService pickup = new AddOnService("Airport Pickup", 800);
        AddOnService extraBed = new AddOnService("Extra Bed", 700);

        // Attach services to reservation
        serviceMap.put("S45", new ArrayList<>());
        serviceMap.get("S45").add(breakfast);
        serviceMap.get("S45").add(extraBed);

        serviceMap.put("D21", new ArrayList<>());
        serviceMap.get("D21").add(pickup);

        // Display add-ons + cost
        System.out.println("===== ADD-ON SERVICES REPORT =====\n");

        for (String reservationId : serviceMap.keySet()) {

            System.out.println("Reservation ID: " + reservationId);

            double total = 0;

            for (AddOnService s : serviceMap.get(reservationId)) {

                System.out.println("Service: " + s.name + " | Cost: " + s.price);
                total += s.price;
            }

            System.out.println("Total Add-On Cost: " + total + "\n");
        }

        System.out.println("Core booking & inventory remain unchanged.");
    }
}