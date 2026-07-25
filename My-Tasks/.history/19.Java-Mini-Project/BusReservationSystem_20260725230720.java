import java.util.ArrayList;
import java.util.Scanner;

// Bus Class
class Bus {
    private int busId;
    private String busName;
    private String source;
    private String destination;
    private int capacity;
    private int availableSeats;

    public Bus(int busId, String busName, String source, String destination, int capacity) {
        this.busId = busId;
        this.busName = busName;
        this.source = source;
        this.destination = destination;
        this.capacity = capacity;
        this.availableSeats = capacity;
    }

    public int getBusId() {
        return busId;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "-------------------------------------" +
                "\nBus ID          : " + busId +
                "\nBus Name        : " + busName +
                "\nSource          : " + source +
                "\nDestination     : " + destination +
                "\nCapacity        : " + capacity +
                "\nAvailable Seats : " + availableSeats +
                "\n-------------------------------------";
    }
}

// Booking Class
class Booking {
    private int bookingId;
    private String passengerName;
    private int busId;

    public Booking(int bookingId, String passengerName, int busId) {
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.busId = busId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getBusId() {
        return busId;
    }

    @Override
    public String toString() {
        return "-------------------------------------" +
                "\nBooking ID : " + bookingId +
                "\nPassenger  : " + passengerName +
                "\nBus ID     : " + busId +
                "\n-------------------------------------";
    }
}

// Bus Service Class
class BusService {

    private ArrayList<Bus> buses = new ArrayList<>();

    public void addBus(Bus bus) {
        buses.add(bus);
        System.out.println("Bus added successfully.");
    }

    public void viewBuses() {

        if (buses.isEmpty()) {
            System.out.println("No buses available.");
            return;
        }

        for (Bus bus : buses) {
            System.out.println(bus);
        }
    }

    public Bus searchBus(int id) {

        for (Bus bus : buses) {
            if (bus.getBusId() == id) {
                return bus;
            }
        }

        return null;
    }

    public void updateCapacity(int id, int newCapacity) {

        Bus bus = searchBus(id);

        if (bus == null) {
            System.out.println("Bus not found.");
            return;
        }

        int bookedSeats = bus.getCapacity() - bus.getAvailableSeats();

        if (newCapacity < bookedSeats) {
            System.out.println("Cannot reduce capacity below booked seats.");
            return;
        }

        bus.setCapacity(newCapacity);
        bus.setAvailableSeats(newCapacity - bookedSeats);

        System.out.println("Capacity updated successfully.");
    }

    public void deleteBus(int id) {

        Bus bus = searchBus(id);

        if (bus != null) {
            buses.remove(bus);
            System.out.println("Bus deleted successfully.");
        } else {
            System.out.println("Bus not found.");
        }
    }
}

// Booking Service Class
class BookingService {

    private ArrayList<Booking> bookings = new ArrayList<>();

    public void bookTicket(int bookingId, String passengerName, Bus bus) {

        if (bus.getAvailableSeats() <= 0) {
            System.out.println("No seats available.");
            return;
        }

        bookings.add(new Booking(bookingId, passengerName, bus.getBusId()));
        bus.setAvailableSeats(bus.getAvailableSeats() - 1);

        System.out.println("Ticket booked successfully.");
    }

    public void viewBookings() {

        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public void cancelBooking(int bookingId, BusService busService) {

        Booking found = null;

        for (Booking booking : bookings) {

            if (booking.getBookingId() == bookingId) {
                found = booking;
                break;
            }
        }

        if (found == null) {
            System.out.println("Booking not found.");
            return;
        }

        Bus bus = busService.searchBus(found.getBusId());

        if (bus != null) {
            bus.setAvailableSeats(bus.getAvailableSeats() + 1);
        }

        bookings.remove(found);

        System.out.println("Booking cancelled successfully.");
    }
}

// Main Class
public class BusReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BusService busService = new BusService();
        BookingService bookingService = new BookingService();

        while (true) {
            System.out.println("\n========== BUS RESERVATION SYSTEM ==========");
            System.out.println("1. Add Bus");
            System.out.println("2. View All Buses");
            System.out.println("3. Update Bus Capacity");
            System.out.println("4. Delete Bus");
            System.out.println("5. Book Ticket");
            System.out.println("6. View All Bookings");
            System.out.println("7. Cancel Booking");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Bus ID: ");
                    int busId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Bus Name: ");
                    String busName = scanner.nextLine();

                    System.out.print("Enter Source: ");
                    String source = scanner.nextLine();

                    System.out.print("Enter Destination: ");
                    String destination = scanner.nextLine();

                    System.out.print("Enter Capacity: ");
                    int capacity = scanner.nextInt();

                    busService.addBus(new Bus(busId, busName, source, destination, capacity));
                    break;
                case 2:
                    busService.viewBuses();
                    break;
                case 3:
                    System.out.print("Enter Bus ID: ");
                    busId = scanner.nextInt();

                    System.out.print("Enter New Capacity: ");
                    capacity = scanner.nextInt();

                    busService.updateCapacity(busId, capacity);
                    break;
                case 4:
                    System.out.print("Enter Bus ID: ");
                    busId = scanner.nextInt();

                    busService.deleteBus(busId);
                    break;
                case 5:
                    System.out.print("Enter Booking ID: ");
                    int bookingId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Passenger Name: ");
                    String passengerName = scanner.nextLine();

                    System.out.print("Enter Bus ID: ");
                    busId = scanner.nextInt();

                    Bus bus = busService.searchBus(busId);

                    if (bus != null) {
                        bookingService.bookTicket(bookingId, passengerName, bus);
                    } else {
                        System.out.println("Bus not found.");
                    }

                    break;
                case 6:
                    bookingService.viewBookings();
                    break;
                case 7:
                    System.out.print("Enter Booking ID: ");
                    bookingId = scanner.nextInt();

                    bookingService.cancelBooking(bookingId, busService);
                    break;
                case 8:
                    System.out.println("Thank you for using the Bus Reservation System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}