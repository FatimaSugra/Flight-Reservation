import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationHistory reservationHistory = new ReservationHistory();

        // Flight instances with cost per seat
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(new Flight(101, "New York", "Los Angeles", "2024-08-20", "10:00 AM", 300, 150.00));
        flights.add(new Flight(102, "Chicago", "Miami", "2024-08-20", "12:00 PM", 250, 130.00));
        flights.add(new Flight(103, "San Francisco", "New York", "2024-08-21", "1:00 PM", 400, 200.00));
        flights.add(new Flight(104, "Boston", "Seattle", "2024-08-21", "2:00 PM", 350, 180.00));
        flights.add(new Flight(105, "Dallas", "Atlanta", "2024-08-22", "3:00 PM", 200, 120.00));
        flights.add(new Flight(106, "Denver", "Phoenix", "2024-08-22", "4:00 PM", 220, 140.00));
        flights.add(new Flight(107, "Houston", "San Diego", "2024-08-23", "5:00 PM", 280, 150.00));
        flights.add(new Flight(108, "Philadelphia", "Orlando", "2024-08-23", "6:00 PM", 240, 160.00));
        flights.add(new Flight(109, "Atlanta", "Las Vegas", "2024-08-24", "7:00 PM", 320, 170.00));
        flights.add(new Flight(110, "Seattle", "Chicago", "2024-08-24", "8:00 PM", 270, 130.00));
        // Add more flight instances as needed

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                System.out.print("Enter your CNIC: ");
                String cnic = scanner.nextLine();
                System.out.print("Enter your phone number: ");
                String phoneNumber = scanner.nextLine();
                User.register(username, password, name, cnic, phoneNumber);
            } else if (choice == 2) {
                loggedIn = User.login(username, password);
            }
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Search Flight");
            System.out.println("2. Book Seat");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Check-in");
            System.out.println("5. Make Payment");
            System.out.println("6. View Reservation History");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter date (YYYY-MM-DD) or leave blank: ");
                    String searchDate = scanner.nextLine();
                    System.out.print("Enter time or leave blank: ");
                    String searchTime = scanner.nextLine();
                    System.out.print("Enter location (origin or destination) or leave blank: ");
                    String searchLocation = scanner.nextLine();

                    System.out.println("Matching Flights:");
                    for (Flight flight : flights) {
                        if (flight.matchesSearch(searchDate, searchTime, searchLocation)) {
                            flight.displayFlightInfo();
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter flight number: ");
                    int bookFlightNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Flight flightToBook = null;
                    for (Flight flight : flights) {
                        if (flight.getFlightNumber() == bookFlightNumber) {
                            flightToBook = flight;
                            break;
                        }
                    }

                    if (flightToBook != null) {
                        System.out.print("Enter seat number: ");
                        int seatNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter your name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter your CNIC: ");
                        String cnic = scanner.nextLine();
                        System.out.print("Enter your passport number: ");
                        String passportNumber = scanner.nextLine();
                        System.out.print("Enter seat type (Premium, Business, Economy): ");
                        String seatType = scanner.nextLine();

                        if (flightToBook.bookSeat(seatNumber, name, cnic, passportNumber, seatType)) {
                            reservationHistory.addReservation("Flight " + bookFlightNumber + ", Seat " + seatNumber + ", " + seatType);
                        }
                    } else {
                        System.out.println("Flight not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter flight number: ");
                    int cancelFlightNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Flight flightToCancel = null;
                    for (Flight flight : flights) {
                        if (flight.getFlightNumber() == cancelFlightNumber) {
                            flightToCancel = flight;
                            break;
                        }
                    }

                    if (flightToCancel != null) {
                        System.out.print("Enter seat number: ");
                        int seatToCancel = scanner.nextInt();
                        flightToCancel.cancelSeat(seatToCancel);
                    } else {
                        System.out.println("Flight not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter flight number: ");
                    int checkInFlightNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Flight flightToCheckIn = null;
                    for (Flight flight : flights) {
                        if (flight.getFlightNumber() == checkInFlightNumber) {
                            flightToCheckIn = flight;
                            break;
                        }
                    }

                    if (flightToCheckIn != null) {
                        System.out.print("Enter seat number: ");
                        int seatToCheckIn = scanner.nextInt();
                        flightToCheckIn.checkIn(seatToCheckIn);
                    } else {
                        System.out.println("Flight not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter flight number: ");
                    int paymentFlightNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Flight flightToPay = null;
                    for (Flight flight : flights) {
                        if (flight.getFlightNumber() == paymentFlightNumber) {
                            flightToPay = flight;
                            break;
                        }
                    }

                    if (flightToPay != null) {
                        System.out.print("Enter seat number: ");
                        int seatNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        flightToPay.makePayment(seatNumber);
                    } else {
                        System.out.println("Flight not found!");
                    }
                    break;

                case 6:
                    reservationHistory.displayHistory();
                    break;

                case 7:
                    exit = true;
                    System.out.println("Thank you for using the Air Reservation System!");
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

        scanner.close();
    }
}
