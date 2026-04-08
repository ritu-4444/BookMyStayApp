import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    void decrease(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

class BookingQueue {
    Queue<Reservation> queue = new LinkedList<>();

    void add(Reservation r) {
        queue.add(r);
    }

    Reservation next() {
        return queue.poll();
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }
}

class RoomAllocationService {

    private HashMap<String, Set<String>> allocated = new HashMap<>();
    private int idCounter = 1;

    String generateRoomId(String type) {
        return type.substring(0, 1) + idCounter++;
    }

    void process(BookingQueue queue, RoomInventory inventory) {

        while (!queue.isEmpty()) {

            Reservation r = queue.next();
            String type = r.roomType;

            if (inventory.getAvailability(type) > 0) {

                String roomId = generateRoomId(type);

                allocated.putIfAbsent(type, new HashSet<>());
                allocated.get(type).add(roomId);

                inventory.decrease(type);

                System.out.println("Confirmed: " + r.guestName + " -> " + type + " | Room ID: " + roomId);

            } else {
                System.out.println("Failed: " + r.guestName + " -> No rooms available");
            }
        }
    }
}

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        BookingQueue queue = new BookingQueue();

        queue.add(new Reservation("Ritu", "Single Room"));
        queue.add(new Reservation("Amit", "Single Room"));
        queue.add(new Reservation("John", "Single Room"));

        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();

        service.process(queue, inventory);
    }
}