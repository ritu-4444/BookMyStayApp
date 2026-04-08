import java.util.HashMap;
import java.util.Map;

abstract class Room {
    String type;
    int beds;
    double price;

    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    void displayDetails() {
        System.out.println("Room Type : " + type);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : " + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 1000);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 2000);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 5000);
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0);
        inventory.put("Suite Room", 2);
    }

    public Map<String, Integer> getAllRooms() {
        return inventory;
    }
}

class RoomSearchService {

    public void searchAvailableRooms(RoomInventory inventory) {

        System.out.println("===== Available Rooms (v4.0) =====");

        for (Map.Entry<String, Integer> entry : inventory.getAllRooms().entrySet()) {

            String type = entry.getKey();
            int available = entry.getValue();

            if (available > 0) {

                Room room;

                if (type.equals("Single Room")) {
                    room = new SingleRoom();
                } else if (type.equals("Double Room")) {
                    room = new DoubleRoom();
                } else {
                    room = new SuiteRoom();
                }

                room.displayDetails();
                System.out.println("Available : " + available);
                System.out.println("---------------------------");
            }
        }
    }
}

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        RoomSearchService search = new RoomSearchService();

        search.searchAvailableRooms(inventory);
    }
}