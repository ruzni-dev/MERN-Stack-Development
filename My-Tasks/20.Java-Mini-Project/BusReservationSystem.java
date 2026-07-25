import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BusReservationSystem {

    private final List<Bus> buses;
    private final List<Booking> bookings;
    private final Scanner scanner;

    public BusReservationSystem() {
        buses = new ArrayList<>();
        bookings = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        BusReservationSystem app = new BusReservationSystem();
        app.start();
    }

    private void start() {
        System.out.println("=== Bus Reservation System ===");

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    manageBuses();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using the Bus Reservation System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number from 1 to 5.");
            }
            System.out.println();
        }
    }

    private void showMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. Bus Management");
        System.out.println("2. Book Ticket");
        System.out.println("3. View Bookings");
        System.out.println("4. Cancel Booking");
        System.out.println("5. Exit");
    }

    private void manageBuses() {
        boolean backToMain = false;
        while (!backToMain) {
            showBusManagementMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    addBus();
                    break;
                case 2:
                    viewAllBuses();
                    break;
                case 3:
                    updateBusCapacity();
                    break;
                case 4:
                    deleteBus();
                    break;
                case 5:
                    searchBus();
                    break;
                case 6:
                    backToMain = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number from 1 to 6.");
            }
            System.out.println();
        }
    }

    private void showBusManagementMenu() {
        System.out.println("\nBus Management");
        System.out.println("1. Add a new bus");
        System.out.println("2. View all available buses");
        System.out.println("3. Update bus capacity");
        System.out.println("4. Delete a bus");
        System.out.println("5. Search for a bus by bus number");
        System.out.println("6. Return to main menu");
    }

    private void addBus() {
        System.out.println("\nAdd New Bus");
        String busNumber = readNonEmptyString("Enter bus number: ");
        if (findBusByNumber(busNumber).isPresent()) {
            System.out.println("A bus with this number already exists. Please use a unique bus number.");
            return;
        }

        String source = readNonEmptyString("Enter source: ");
        String destination = readNonEmptyString("Enter destination: ");
        int capacity = readPositiveInt("Enter capacity: ");

        buses.add(new Bus(busNumber, source, destination, capacity));
        System.out.println("Bus added successfully.");
    }

    private void viewAllBuses() {
        System.out.println("\nAvailable Buses:");
        if (buses.isEmpty()) {
            System.out.println("No buses are currently available.");
            return;
        }
        for (Bus bus : buses) {
            System.out.println(bus);
        }
    }

    private void updateBusCapacity() {
        System.out.println("\nUpdate Bus Capacity");
        String busNumber = readNonEmptyString("Enter bus number to update: ");
        Optional<Bus> optionalBus = findBusByNumber(busNumber);
        if (optionalBus.isEmpty()) {
            System.out.println("Bus not found with number: " + busNumber);
            return;
        }

        Bus bus = optionalBus.get();
        System.out.println("Current capacity: " + bus.getCapacity() + ", Booked seats: " + bus.getBookedSeats());
        int newCapacity = readPositiveInt("Enter new capacity: ");
        if (newCapacity < bus.getBookedSeats()) {
            System.out.println("New capacity cannot be less than the number of seats already booked.");
            return;
        }
        bus.setCapacity(newCapacity);
        System.out.println("Bus capacity updated successfully.");
    }

    private void deleteBus() {
        System.out.println("\nDelete Bus");
        String busNumber = readNonEmptyString("Enter bus number to delete: ");
        Optional<Bus> optionalBus = findBusByNumber(busNumber);
        if (optionalBus.isEmpty()) {
            System.out.println("Bus not found with number: " + busNumber);
            return;
        }
        Bus bus = optionalBus.get();
        if (bus.getBookedSeats() > 0) {
            System.out.println("Cannot delete a bus with existing bookings. Cancel bookings first.");
            return;
        }
        buses.remove(bus);
        System.out.println("Bus deleted successfully.");
    }

    private void searchBus() {
        System.out.println("\nSearch Bus");
        String busNumber = readNonEmptyString("Enter bus number to search: ");
        Optional<Bus> optionalBus = findBusByNumber(busNumber);
        if (optionalBus.isEmpty()) {
            System.out.println("Bus not found with number: " + busNumber);
            return;
        }
        System.out.println(optionalBus.get());
    }

    private void bookTicket() {
        System.out.println("\nBook Ticket");
        if (buses.isEmpty()) {
            System.out.println("No buses are available to book. Please add a bus first.");
            return;
        }

        String passengerName = readNonEmptyString("Enter passenger name: ");
        String passengerId = readNonEmptyString("Enter passenger ID: ");
        if (findBookingByPassengerId(passengerId).isPresent()) {
            System.out.println("A booking already exists with this passenger ID. Passenger ID must be unique.");
            return;
        }

        String busNumber = readNonEmptyString("Enter bus number to book: ");
        Optional<Bus> optionalBus = findBusByNumber(busNumber);
        if (optionalBus.isEmpty()) {
            System.out.println("Bus not found with number: " + busNumber);
            return;
        }

        Bus bus = optionalBus.get();
        if (!bus.hasAvailableSeats()) {
            System.out.println("No seats available on this bus. Please choose another bus.");
            return;
        }

        bus.incrementBookedSeats();
        bookings.add(new Booking(passengerId, passengerName, bus));
        System.out.println("Ticket booked successfully for " + passengerName + " on bus " + busNumber + ".");
    }

    private void viewBookings() {
        System.out.println("\nCurrent Bookings:");
        if (bookings.isEmpty()) {
            System.out.println("No bookings have been made yet.");
            return;
        }
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private void cancelBooking() {
        System.out.println("\nCancel Booking");
        String passengerId = readNonEmptyString("Enter passenger ID to cancel: ");
        Optional<Booking> optionalBooking = findBookingByPassengerId(passengerId);
        if (optionalBooking.isEmpty()) {
            System.out.println("Booking not found with passenger ID: " + passengerId);
            return;
        }
        Booking booking = optionalBooking.get();
        Optional<Bus> optionalBus = findBusByNumber(booking.getBusNumber());
        if (optionalBus.isPresent()) {
            optionalBus.get().decrementBookedSeats();
        }
        bookings.remove(booking);
        System.out.println("Booking cancelled successfully for passenger ID: " + passengerId);
    }

    private Optional<Bus> findBusByNumber(String busNumber) {
        return buses.stream()
                .filter(bus -> bus.getBusNumber().equalsIgnoreCase(busNumber.trim()))
                .findFirst();
    }

    private Optional<Booking> findBookingByPassengerId(String passengerId) {
        return bookings.stream()
                .filter(booking -> booking.getPassengerId().equalsIgnoreCase(passengerId.trim()))
                .findFirst();
    }

    private String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please enter a valid value.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Please enter a valid integer.");
            }
        }
    }

    private int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Please enter a positive number greater than zero.");
        }
    }

    private static class Bus {
        private final String busNumber;
        private final String source;
        private final String destination;
        private int capacity;
        private int bookedSeats;

        public Bus(String busNumber, String source, String destination, int capacity) {
            this.busNumber = busNumber.trim();
            this.source = source.trim();
            this.destination = destination.trim();
            this.capacity = capacity;
            this.bookedSeats = 0;
        }

        public String getBusNumber() {
            return busNumber;
        }

        public String getSource() {
            return source;
        }

        public String getDestination() {
            return destination;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getBookedSeats() {
            return bookedSeats;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public boolean hasAvailableSeats() {
            return bookedSeats < capacity;
        }

        public void incrementBookedSeats() {
            if (hasAvailableSeats()) {
                bookedSeats++;
            }
        }

        public void decrementBookedSeats() {
            if (bookedSeats > 0) {
                bookedSeats--;
            }
        }

        @Override
        public String toString() {
            return String.format("Bus Number: %s | Source: %s | Destination: %s | Capacity: %d | Booked Seats: %d",
                    busNumber, source, destination, capacity, bookedSeats);
        }
    }

    private static class Booking {
        private final String passengerId;
        private final String passengerName;
        private final String busNumber;
        private final String source;
        private final String destination;

        public Booking(String passengerId, String passengerName, Bus bus) {
            this.passengerId = passengerId.trim();
            this.passengerName = passengerName.trim();
            this.busNumber = bus.getBusNumber();
            this.source = bus.getSource();
            this.destination = bus.getDestination();
        }

        public String getPassengerId() {
            return passengerId;
        }

        public String getBusNumber() {
            return busNumber;
        }

        @Override
        public String toString() {
            return String.format("Passenger ID: %s | Name: %s | Bus Number: %s | Source: %s | Destination: %s",
                    passengerId, passengerName, busNumber, source, destination);
        }
    }
}
