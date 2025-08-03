import java.util.ArrayList;

public class Flight {
    private int flightNumber;
    private String origin;
    private String destination;
    private String date;
    private String departureTime;
    private String[] seats;
    private ArrayList<String> waitlist;
    private double costPerSeat;  // Cost per seat
    private boolean isPaid;     // Payment status
    private boolean[] seatPayments; // Payment status for each seat

    public Flight(int flightNumber, String origin, String destination, String date, String departureTime, int seatCount, double costPerSeat) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.departureTime = departureTime;
        this.seats = new String[seatCount];
        this.seatPayments = new boolean[seatCount]; // Track payments for each seat
        for (int i = 0; i < seatCount; i++) {
            seats[i] = "Available";
            seatPayments[i] = false; // Initially, no seat is paid
        }
        this.waitlist = new ArrayList<>();
        this.costPerSeat = costPerSeat;
        this.isPaid = false;
    }

    // Getters and Setters
    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String[] getSeats() {
        return seats;
    }

    public void setSeats(String[] seats) {
        this.seats = seats;
    }

    public ArrayList<String> getWaitlist() {
        return waitlist;
    }

    public void setWaitlist(ArrayList<String> waitlist) {
        this.waitlist = waitlist;
    }

    public double getCostPerSeat() {
        return costPerSeat;
    }

    public void setCostPerSeat(double costPerSeat) {
        this.costPerSeat = costPerSeat;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    // Display seat information
    public void displaySeats() {
        System.out.println("Seat Number | Status");
        for (int i = 0; i < seats.length; i++) {
            System.out.println((i + 1) + " | " + seats[i]);
        }
        System.out.println();
    }

    // Book a seat
    public boolean bookSeat(int seatNumber, String passengerName, String cnic, String passportNumber, String seatType) {
        if (seatNumber < 1 || seatNumber > seats.length || !seats[seatNumber - 1].equals("Available")) {
            System.out.println("Seat not available or invalid seat number!");
            return false;
        }
        seats[seatNumber - 1] = "Reserved by " + passengerName + " (" + seatType + ")";
        System.out.println("Seat " + seatNumber + " booked successfully!");
        return true;
    }

    // Cancel a seat reservation
    public void cancelSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > seats.length || seats[seatNumber - 1].equals("Available")) {
            System.out.println("No such booking exists or seat not reserved!");
            return;
        }
        if (seats[seatNumber - 1].startsWith("Checked In")) {
            System.out.println("Cannot cancel the reservation as the seat is checked in!");
            return;
        }
        seats[seatNumber - 1] = "Available";
        System.out.println("Booking canceled successfully!");
    }

    // Check-in a seat
    public void checkIn(int seatNumber) {
        if (seatNumber < 1 || seatNumber > seats.length || seats[seatNumber - 1].equals("Available")) {
            System.out.println("No such booking exists or seat not reserved!");
            return;
        }
        if (!seatPayments[seatNumber - 1]) {
            System.out.println("Cannot check in as payment is not completed!");
            return;
        }
        seats[seatNumber - 1] = "Checked In";
        System.out.println("Checked in successfully!");
    }

    // Add a passenger to the waitlist
    public void addToWaitlist(String passenger) {
        waitlist.add(passenger);
        System.out.println("Added to waitlist!");
    }

    // Display flight information
    public void displayFlightInfo() {
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Origin: " + origin);
        System.out.println("Destination: " + destination);
        System.out.println("Date: " + date);
        System.out.println("Departure Time: " + departureTime);
        System.out.println("Cost per Seat: $" + costPerSeat);
        displaySeats();
    }

    // Search functionality
    public boolean matchesSearch(String searchDate, String searchTime, String searchLocation) {
        boolean dateMatches = searchDate == null || searchDate.isEmpty() || date.equals(searchDate);
        boolean timeMatches = searchTime == null || searchTime.isEmpty() || departureTime.contains(searchTime);
        boolean locationMatches = searchLocation == null || searchLocation.isEmpty() || 
                                  origin.contains(searchLocation) || destination.contains(searchLocation);

        return dateMatches && timeMatches && locationMatches;
    }

    // Pay for the flight
    public void makePayment(int seatNumber) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println("Invalid seat number!");
            return;
        }
        double paymentAmount = costPerSeat;
        if (paymentAmount > 0) {
            seatPayments[seatNumber - 1] = true;
            System.out.println("Payment of $" + paymentAmount + " for seat " + seatNumber + " successful!");
        } else {
            System.out.println("Invalid amount. Payment failed.");
        }
    }
}
